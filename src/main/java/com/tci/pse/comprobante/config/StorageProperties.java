package com.tci.pse.comprobante.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ConfigurationProperties("tci-pse-comprobante.features")
@Validated
public class StorageProperties {

    private CredentialProperties credentials;

    public CredentialProperties getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialProperties credentials) {
        this.credentials = credentials;
    }

    public static class CredentialProperties {

        @NotEmpty(message = "projectId no puede ser nulo")
        private String projectId;

        @NotEmpty(message = "encodedKey no puede ser vacío")
        private String encodedKey;

        @NotEmpty(message = "configServer no puede ser vacío")
        private String configServer;

        @NotEmpty(message = "bucket no puede ser vacío")
        private String bucket;

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getEncodedKey() {
            return encodedKey;
        }

        public void setEncodedKey(String encodedKey) {
            this.encodedKey = encodedKey;
        }

        public String getConfigServer() {
            return configServer;
        }

        public void setConfigServer(String configServer) {
            this.configServer = configServer;
        }
    }

}
