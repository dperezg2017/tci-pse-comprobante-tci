package com.tci.pse.comprobante.config;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteColaRequest;
import com.tci.pse.comprobante.core.util.Util;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SenderConfiguration {

    private final PubSubProperties pubSubProperties;

    public SenderConfiguration(PubSubProperties pubSubProperties) {
        this.pubSubProperties = pubSubProperties;
    }

    @Bean
    public DirectChannel outputChannelDeclare() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel inputChannelDeclare() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel outputChannelDeclareMapping() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel inputChannelDeclareMapping() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = Util.OUTPUT_CHANNEL_DELCARE)
    public MessageHandler messageSendDeclare(PubSubTemplate pubSubTemplate) {
        return new PubSubMessageHandler(pubSubTemplate, pubSubProperties.getTopics().getNameTopicDeclare());
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapterDeclare(
            @Qualifier(Util.INPUT_CHANNEL_DELCARE) MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, pubSubProperties.getSubscribers().getNameSubscriberDeclare());
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.AUTO_ACK);
        adapter.setPayloadType(ComprobanteColaRequest.class);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = Util.OUTPUT_CHANNEL_DELCARE_MAPPING)
    public MessageHandler messageSendDeclareMapping(PubSubTemplate pubSubTemplate) {
        return new PubSubMessageHandler(pubSubTemplate, pubSubProperties.getTopics().getNameTopicDeclareMapping());
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapterDeclareMapping(
            @Qualifier(Util.INPUT_CHANNEL_DELCARE_MAPPING) MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {

        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, pubSubProperties.getSubscribers().getNameSubscriberDeclareMapping());
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.AUTO_ACK);
        adapter.setPayloadType(ComprobanteColaRequest.class);
        return adapter;
    }

}