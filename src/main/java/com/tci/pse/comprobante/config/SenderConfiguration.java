package com.tci.pse.comprobante.config;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.integration.channel.DirectChannel;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SenderConfiguration {

    private static final String TOPIC_DECLARACION = "tci-pse-topic-dev-declare";
    private static final String SUSCRIPTION_NAME_DECLARACION = "tci-pse-topic-dev-declare.comprobante";


    @Bean
    public DirectChannel pubSubOutputChannel() {
        return new DirectChannel();
    }
    @Bean
    public DirectChannel pubSubInputChannel() {
        return new DirectChannel();
    }


    @MessagingGateway(defaultRequestChannel = "pubSubOutputChannel")
    public interface PubSubComprobanteDeclareGateway {
        void sendComprobanteToPubSubDeclare(ComprobanteCola comprobanteDto);
    }

    @Bean
    @ServiceActivator(inputChannel = "pubSubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubSubTemplate) {
        return new PubSubMessageHandler(pubSubTemplate, TOPIC_DECLARACION);
    }


    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapterDeclareComprobante(
            @Qualifier("pubSubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {

        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, SUSCRIPTION_NAME_DECLARACION);
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.AUTO_ACK);
        adapter.setPayloadType(ComprobanteCola.class);
        return adapter;
    }

}