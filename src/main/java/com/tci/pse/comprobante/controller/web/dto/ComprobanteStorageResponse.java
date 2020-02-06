package com.tci.pse.comprobante.controller.web.dto;

public class ComprobanteStorageResponse {

    private String link;

    public ComprobanteStorageResponse(String link) {
        this.link = link;
    }

    public ComprobanteStorageResponse() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ComprobanteStorageResponse{" +
                "link='" + link + '\'' +
                '}';
    }
}
