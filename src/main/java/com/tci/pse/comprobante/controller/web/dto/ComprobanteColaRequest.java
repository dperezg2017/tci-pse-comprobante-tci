package com.tci.pse.comprobante.controller.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ComprobanteColaRequest implements Serializable {

    private static final long serialVersionUID = -6503308210888015809L;

    @JsonProperty("idTransaccion")
    @NotNull(message = "No puede ser vacio")
    private Long idTransaccion;
    @NotNull(message = "No puede ser vacio")
    private String nombreDocumento;
    @NotNull(message = "No puede ser vacio")
    private String idFileInStorage;

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

    public String getIdFileInStorage() {
        return idFileInStorage;
    }

    public void setIdFileInStorage(String idFileInStorage) {
        this.idFileInStorage = idFileInStorage;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, true);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
