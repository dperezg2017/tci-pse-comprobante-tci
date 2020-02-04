package com.tci.pse.comprobante.service;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;

public interface ComprobantesService {

    void enviarColaDeclareAux (ComprobanteCola comprobanteDto);

    String enviarStorage(String json);
}
