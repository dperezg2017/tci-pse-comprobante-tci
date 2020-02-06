package com.tci.pse.comprobante.controller.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ComprobanteStorageRequest {

    @NotNull(message = "No debe ser vacio")
    private Long idTransaccion;

    @NotEmpty(message = "No debe ser vacio")
    private String nombreDocumento;

    @NotEmpty(message = "No debe ser vacio")
    private byte[] xmlZip;

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public byte[] getXmlZip() {
        return xmlZip.clone();
    }

    public void setXmlZip(byte[] xmlZip) {
        this.xmlZip = xmlZip.clone();
    }
}
