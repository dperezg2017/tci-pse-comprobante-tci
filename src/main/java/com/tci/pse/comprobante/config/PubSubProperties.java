package com.tci.pse.comprobante.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("tci-pse-comprobante.features.pubsub")
@Validated
public class PubSubProperties {

    private TopicsProperties topics;

    private SubscribersProperties subscribers;

    public TopicsProperties getTopics() {
        return topics;
    }

    public void setTopics(TopicsProperties topics) {
        this.topics = topics;
    }

    public SubscribersProperties getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(SubscribersProperties subscribers) {
        this.subscribers = subscribers;
    }

    public static class TopicsProperties {

        @NotEmpty(message = "nameTopicDeclare no puede ser nulo")
        private String nameTopicDeclare;

        @NotEmpty(message = "nameTopicDeclareMapping no puede ser nulo")
        private String nameTopicDeclareMapping;

        @NotEmpty(message = "nameTopicDeclareContigencia no puede ser nulo")
        private String nameTopicDeclareContigencia;

        @NotEmpty(message = "nameTopicDeclareOtorgamiento no puede ser nulo")
        private String nameTopicDeclareOtorgamiento;

        @NotEmpty(message = "nameTopicDeclareStatus no puede ser nulo")
        private String nameTopicDeclareStatus;

        @NotEmpty(message = "nameTopicDeclareReproceso no puede ser nulo")
        private String nameTopicDeclareReproceso;

        @NotEmpty(message = "nameTopicDeclareSincronizaEstado no puede ser nulo")
        private String nameTopicDeclareSincronizaEstado;

        @NotEmpty(message = "nameTopicDeclareError no puede ser nulo")
        private String nameTopicDeclareError;

        public String getNameTopicDeclare() {
            return nameTopicDeclare;
        }

        public void setNameTopicDeclare(String nameTopicDeclare) {
            this.nameTopicDeclare = nameTopicDeclare;
        }

        public String getNameTopicDeclareMapping() {
            return nameTopicDeclareMapping;
        }

        public void setNameTopicDeclareMapping(String nameTopicDeclareMapping) {
            this.nameTopicDeclareMapping = nameTopicDeclareMapping;
        }

        public String getNameTopicDeclareContigencia() {
            return nameTopicDeclareContigencia;
        }

        public void setNameTopicDeclareContigencia(String nameTopicDeclareContigencia) {
            this.nameTopicDeclareContigencia = nameTopicDeclareContigencia;
        }

        public String getNameTopicDeclareOtorgamiento() {
            return nameTopicDeclareOtorgamiento;
        }

        public void setNameTopicDeclareOtorgamiento(String nameTopicDeclareOtorgamiento) {
            this.nameTopicDeclareOtorgamiento = nameTopicDeclareOtorgamiento;
        }

        public String getNameTopicDeclareStatus() {
            return nameTopicDeclareStatus;
        }

        public void setNameTopicDeclareStatus(String nameTopicDeclareStatus) {
            this.nameTopicDeclareStatus = nameTopicDeclareStatus;
        }

        public String getNameTopicDeclareReproceso() {
            return nameTopicDeclareReproceso;
        }

        public void setNameTopicDeclareReproceso(String nameTopicDeclareReproceso) {
            this.nameTopicDeclareReproceso = nameTopicDeclareReproceso;
        }

        public String getNameTopicDeclareSincronizaEstado() {
            return nameTopicDeclareSincronizaEstado;
        }

        public void setNameTopicDeclareSincronizaEstado(String nameTopicDeclareSincronizaEstado) {
            this.nameTopicDeclareSincronizaEstado = nameTopicDeclareSincronizaEstado;
        }

        public String getNameTopicDeclareError() {
            return nameTopicDeclareError;
        }

        public void setNameTopicDeclareError(String nameTopicDeclareError) {
            this.nameTopicDeclareError = nameTopicDeclareError;
        }
    }

    public static class SubscribersProperties {

        @NotEmpty(message = "nameSubscriberDeclare no puede ser nulo")
        private String nameSubscriberDeclare;

        @NotEmpty(message = "nameSubscriberDeclareMapping no puede ser nulo")
        private String nameSubscriberDeclareMapping;

        @NotEmpty(message = "nameSubscriberDeclareContigencia no puede ser nulo")
        private String nameSubscriberDeclareContigencia;

        @NotEmpty(message = "nameSubscriberDeclareOtorgamiento no puede ser nulo")
        private String nameSubscriberDeclareOtorgamiento;

        @NotEmpty(message = "nameSubscriberDeclareStatus no puede ser nulo")
        private String nameSubscriberDeclareStatus;

        @NotEmpty(message = "nameSubscriberDeclareReproceso no puede ser nulo")
        private String nameSubscriberDeclareReproceso;

        @NotEmpty(message = "nameSubscriberDeclareSincronizaEstado no puede ser nulo")
        private String nameSubscriberDeclareSincronizaEstado;

        @NotEmpty(message = "nameSubscriberDeclareError no puede ser nulo")
        private String nameSubscriberDeclareError;


        public String getNameSubscriberDeclare() {
            return nameSubscriberDeclare;
        }

        public void setNameSubscriberDeclare(String nameSubscriberDeclare) {
            this.nameSubscriberDeclare = nameSubscriberDeclare;
        }

        public String getNameSubscriberDeclareMapping() {
            return nameSubscriberDeclareMapping;
        }

        public void setNameSubscriberDeclareMapping(String nameSubscriberDeclareMapping) {
            this.nameSubscriberDeclareMapping = nameSubscriberDeclareMapping;
        }

        public String getNameSubscriberDeclareContigencia() {
            return nameSubscriberDeclareContigencia;
        }

        public void setNameSubscriberDeclareContigencia(String nameSubscriberDeclareContigencia) {
            this.nameSubscriberDeclareContigencia = nameSubscriberDeclareContigencia;
        }

        public String getNameSubscriberDeclareOtorgamiento() {
            return nameSubscriberDeclareOtorgamiento;
        }

        public void setNameSubscriberDeclareOtorgamiento(String nameSubscriberDeclareOtorgamiento) {
            this.nameSubscriberDeclareOtorgamiento = nameSubscriberDeclareOtorgamiento;
        }

        public String getNameSubscriberDeclareStatus() {
            return nameSubscriberDeclareStatus;
        }

        public void setNameSubscriberDeclareStatus(String nameSubscriberDeclareStatus) {
            this.nameSubscriberDeclareStatus = nameSubscriberDeclareStatus;
        }

        public String getNameSubscriberDeclareReproceso() {
            return nameSubscriberDeclareReproceso;
        }

        public void setNameSubscriberDeclareReproceso(String nameSubscriberDeclareReproceso) {
            this.nameSubscriberDeclareReproceso = nameSubscriberDeclareReproceso;
        }

        public String getNameSubscriberDeclareSincronizaEstado() {
            return nameSubscriberDeclareSincronizaEstado;
        }

        public void setNameSubscriberDeclareSincronizaEstado(String nameSubscriberDeclareSincronizaEstado) {
            this.nameSubscriberDeclareSincronizaEstado = nameSubscriberDeclareSincronizaEstado;
        }

        public String getNameSubscriberDeclareError() {
            return nameSubscriberDeclareError;
        }

        public void setNameSubscriberDeclareError(String nameSubscriberDeclareError) {
            this.nameSubscriberDeclareError = nameSubscriberDeclareError;
        }
    }
}
