package com.tci.pse.comprobante.service;


import com.tci.pse.comprobante.controller.web.dto.ComprobanteColaRequest;
import com.tci.pse.comprobante.core.util.Util;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface PubSubGatewayInterface {

    @Gateway(requestChannel = Util.OUTPUT_CHANNEL_DELCARE)
    void sendComprobanteToPubSubDeclare(ComprobanteColaRequest comprobanteDto);

    @Gateway(requestChannel = Util.OUTPUT_CHANNEL_DELCARE_MAPPING)
    void sendComprobanteToPubSubMapping(ComprobanteColaRequest comprobanteDto);
}
