package com.tci.pse.comprobante.controller.web;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteColaRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageResponse;
import com.tci.pse.comprobante.service.ComprobantesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/comprobante")
public class ComprobantesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesController.class);

    @Autowired
    ResourceLoader resourceLoader;

    private ComprobantesService comprobantesService;

    public ComprobantesController(ComprobantesService comprobantesService) {
        this.comprobantesService = comprobantesService;
    }

    @PostMapping(value = "/enviarNuevaColaDeclaracion")
    public HttpEntity<ComprobanteColaRequest> enviarNuevaColaDeclaracion(@RequestBody ComprobanteColaRequest comprobanteColaRequest) {
        try {
            comprobantesService.enviarNuevaColaDeclaracion(comprobanteColaRequest);
            LOGGER.info("ComprobanteColaRequest {}", comprobanteColaRequest.getNombreDocumento());
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error: ", e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/transmitirComprobante")
    public ResponseEntity<ComprobanteStorageResponse> getEnviarStorage(@Valid @RequestBody ComprobanteStorageRequest json) {


        ComprobanteStorageResponse comprobanteStorageResponse = new ComprobanteStorageResponse();

        try {
            comprobanteStorageResponse.setLink(comprobantesService.enviarStorage(json));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(comprobanteStorageResponse, HttpStatus.OK);
    }


}

