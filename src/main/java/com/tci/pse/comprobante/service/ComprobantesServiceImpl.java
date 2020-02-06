package com.tci.pse.comprobante.service;


import com.google.api.client.util.Base64;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.gson.Gson;
import com.tci.pse.comprobante.config.StorageProperties;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteColaRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;
import com.tci.pse.comprobante.controller.web.dto.JsonCredentialStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.tci.pse.comprobante.core.util.Util.*;

@Service
public class ComprobantesServiceImpl implements ComprobantesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesServiceImpl.class);

    private PubSubGatewayInterface pubSubGatewayInterface;

    private final StorageProperties properties;


    public ComprobantesServiceImpl(PubSubGatewayInterface pubSubGatewayInterface, StorageProperties properties) {
        this.pubSubGatewayInterface = pubSubGatewayInterface;
        this.properties = properties;
    }

    @Override
    public void enviarNuevaColaDeclaracion(ComprobanteColaRequest comprobanteDto) {
        pubSubGatewayInterface.sendComprobanteToPubSubDeclare(comprobanteDto);
    }

    @Override
    public String enviarStorage(ComprobanteStorageRequest comprobanteStorageRequest) {
        try{
            Storage storage;
            final Gson gson = new Gson();
            String originalName =String.valueOf(comprobanteStorageRequest.getIdTransaccion())
                            .concat(GUION)
                            .concat(comprobanteStorageRequest.getNombreDocumento());

            List<Acl> acls = new ArrayList<>();
            acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
            String decodeJsonByte = new String(Base64.decodeBase64(properties.getCredentials().getEncodedKey()), StandardCharsets.UTF_8);
            JsonCredentialStorage jsonCredentialStorage = gson.fromJson(decodeJsonByte, JsonCredentialStorage.class);
            storage = StorageOptions
                    .newBuilder()
                    .setCredentials(
                            ServiceAccountCredentials.fromPkcs8(
                                    jsonCredentialStorage.getClientId(),
                                    jsonCredentialStorage.getClientEmail(),
                                    jsonCredentialStorage.getPrivateKey(),
                                    jsonCredentialStorage.getPrivateKeyId(),
                                    null)
                    )
                    .setProjectId(properties.getCredentials().getProjectId())
                    .build()
                    .getService();

            Blob blob =
                    storage.create(
                            BlobInfo
                                    .newBuilder(properties.getCredentials().getBucket(), PACKAGE_XML.concat(SLASH).concat(originalName))
                                    .setAcl(acls)
                                    .build(),
                            comprobanteStorageRequest.getXmlZip());

            LOGGER.info("config-server : {}",properties.getCredentials().getConfigServer());

            return blob.getSelfLink();

        } catch (Exception e) {
            LOGGER.error("config-server: {}",properties.getCredentials().getConfigServer());
            LOGGER.error("ocurrio un error: ", e);
            return e.getMessage();
        }
    }

    @ServiceActivator(inputChannel = INPUT_CHANNEL_DELCARE)
    public void receiveMessageDeclare(final ComprobanteColaRequest comprobanteDto,
                               @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {

        LOGGER.info("mensaje declare: {}",comprobanteDto.toString());
        try{
            ListenableFuture<Void> ackFuture = message.ack();
            ackFuture.get();

        }catch (Exception ex){
            LOGGER.error("ocurrio un error :",ex);
        }

    }

    @ServiceActivator(inputChannel = INPUT_CHANNEL_DELCARE_MAPPING)
    public void receiveMessageDeclareMapping(final ComprobanteColaRequest comprobanteDto,
                               @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {

        LOGGER.info("mensaje mapping : {}",comprobanteDto.toString());
        try{
            ListenableFuture<Void> ackFuture = message.ack();
            ackFuture.get();

        }catch (Exception ex){
            LOGGER.error("ocurrio un error :",ex);
        }

    }


}
