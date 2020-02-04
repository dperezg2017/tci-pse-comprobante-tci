package com.tci.pse.comprobante.controller.web.dto;

public class ComprobanteStorageResponse {


    private String cadenaRespuestaStorage;

    public ComprobanteStorageResponse(String cadenaRespuestaStorage) {
        this.cadenaRespuestaStorage = cadenaRespuestaStorage;
    }

    public String getCadenaRespuestaStorage() {
        return cadenaRespuestaStorage;
    }

    public void setCadenaRespuestaStorage(String cadenaRespuestaStorage) {
        this.cadenaRespuestaStorage = cadenaRespuestaStorage;
    }
}
