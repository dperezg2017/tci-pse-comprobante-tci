package com.tci.pse.comprobante.service;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteColaRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;

public interface ComprobantesService {

    void enviarNuevaColaDeclaracion (ComprobanteColaRequest comprobanteDto);

    String enviarStorage(ComprobanteStorageRequest json);
}
