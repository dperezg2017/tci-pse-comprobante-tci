package com.tci.pse.comprobante.service;


import com.google.api.client.util.Base64;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.gson.Gson;
import com.tci.pse.comprobante.config.SenderConfiguration.PubSubComprobanteDeclareGateway;

import com.tci.pse.comprobante.config.StorageProperties;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageResponse;
import com.tci.pse.comprobante.controller.web.dto.JsonCredentialStorage;
import com.tci.pse.comprobante.core.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;

import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.messaging.handler.annotation.Header;

import org.springframework.stereotype.Service;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComprobantesServiceImpl implements ComprobantesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesServiceImpl.class);

    private PubSubComprobanteDeclareGateway pubSubTemplate;

    private final StorageProperties properties;

    private Storage storage;

    public ComprobantesServiceImpl( PubSubComprobanteDeclareGateway pubSubTemplate, StorageProperties properties) {
        this.pubSubTemplate = pubSubTemplate;
        this.properties=properties;
    }

    @Override
    public void enviarColaDeclareAux(ComprobanteCola comprobanteDto) {


        pubSubTemplate.sendComprobanteToPubSubDeclare(comprobanteDto);

    }

    @Override
    public String enviarStorage(String json) {
        try{
            final Gson gson = new Gson();

            ComprobanteStorageRequest comprobanteStorageRequest = gson.fromJson(json, ComprobanteStorageRequest.class);

            String originalName =
                    comprobanteStorageRequest.getIdTransaccion().toString()
                            .concat(Util.GUION)
                            .concat(comprobanteStorageRequest.getNombreDocumento());


            List<Acl> acls = new ArrayList<Acl>();
            acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

            byte[] jsonByte = Base64.decodeBase64(properties.getCredentials().getEncodedKey());
            String decodeJsonByte = new String(jsonByte);
            JsonCredentialStorage jsonCredentialStorage = gson.fromJson(decodeJsonByte, JsonCredentialStorage.class);
            jsonCredentialStorage.getClient_email();


            storage = StorageOptions
                    .newBuilder()
                    .setCredentials(
                            ServiceAccountCredentials.fromPkcs8(
                                    jsonCredentialStorage.getClient_id(),
                                    jsonCredentialStorage.getClient_email(),
                                    jsonCredentialStorage.getPrivate_key(),
                                    jsonCredentialStorage.getPrivate_key_id(),
                                    null)
                    )
                    .setProjectId(properties.getCredentials().getProjectId())
                    .build()
                    .getService();

            Blob blob =
                    storage.create(
                            BlobInfo
                                    .newBuilder(properties.getCredentials().getBucket(), Util.PACKAGE_STORAGE[0].concat(Util.SLASH).concat(originalName))
                                    .setAcl(acls)
                                    .build(),
                            comprobanteStorageRequest.getXmlZip());


//			ComprobanteStorageResponse responseStorage = new ComprobanteStorageResponse(originalName+"upload /n"+blob.getMediaLink());
            ComprobanteStorageResponse comprobanteStorageResponse = new ComprobanteStorageResponse(blob.getSelfLink());

//            System.out.println("1) Test de config server: "+properties.getCredentials().getConfigServer());

            LOGGER.info("config-server :",properties.getCredentials().getConfigServer());
            return gson.toJson(comprobanteStorageResponse);

        } catch (Exception e) {
            LOGGER.info("config-server :",properties.getCredentials().getConfigServer());
            LOGGER.error("ocurrio un error :",e);
            return e.getMessage();
        }
    }

    @ServiceActivator(inputChannel = "pubSubInputChannel")
    public void receiveMessage(final ComprobanteCola comprobanteDto,
                               @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {


        try{
            ListenableFuture<Void> ackFuture = message.ack();
            ackFuture.get();

        }catch (Exception ex){
            LOGGER.error("ocurrio un error :",ex);
        }

    }


}
