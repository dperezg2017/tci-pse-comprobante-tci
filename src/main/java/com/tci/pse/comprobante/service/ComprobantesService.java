package com.tci.pse.comprobante.service;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;

public interface ComprobantesService {

    void enviarColaDeclareAux (ComprobanteCola comprobanteDto);

    String enviarStorage(ComprobanteStorageRequest json);
}
