package com.tci.pse.comprobante.controller.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonCredentialStorage {

    @SerializedName("type")
    private String type;

    @SerializedName("project_id")
    private String projectId;

    @SerializedName("private_key_id")
    private String privateKeyId;

    @SerializedName("private_key")
    private String privateKey;

    @SerializedName("client_email")
    private String clientEmail;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("auth_uri")
    private String authUri;

    @SerializedName("token_uri")
    private String tokenUri;

    @SerializedName("auth_provider_x509_cert_url")
    private String authProviderX509CertUrl;

    @SerializedName("client_x509_cert_url")
    private String clientX509CertUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPrivateKeyId() {
        return privateKeyId;
    }

    public void setPrivateKeyId(String privateKeyId) {
        this.privateKeyId = privateKeyId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAuthUri() {
        return authUri;
    }

    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    public String getAuthProviderX509CertUrl() {
        return authProviderX509CertUrl;
    }

    public void setAuthProviderX509CertUrl(String authProviderX509CertUrl) {
        this.authProviderX509CertUrl = authProviderX509CertUrl;
    }

    public String getClientX509CertUrl() {
        return clientX509CertUrl;
    }

    public void setClientX509CertUrl(String clientX509CertUrl) {
        this.clientX509CertUrl = clientX509CertUrl;
    }
}
