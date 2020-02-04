package com.tci.pse.comprobante.controller.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;




public class ComprobanteStorageRequest {

    @Valid
    @JsonProperty(
            required = true
    )
    @NotNull(
            message = "El parametro 'idTransaccion' es invalido. El valor ingresado es nulo"
    )
    private Long idTransaccion;

    @Valid
    @JsonProperty(
            required = true
    )
    @NotNull(
            message = "El parametro 'nombreDocumento' es invalido. El valor ingresado es nulo"
    )
    private String nombreDocumento;

    @Valid
    @JsonProperty(
            required = true
    )
    @NotNull(
            message = "El parametro 'xmlZip' es invalido. El valor ingresado es nulo"
    )
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
        return xmlZip;
    }

    public void setXmlZip(byte[] xmlZip) {
        this.xmlZip = xmlZip;
    }
}
