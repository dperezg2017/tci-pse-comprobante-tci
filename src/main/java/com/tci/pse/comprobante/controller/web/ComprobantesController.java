package com.tci.pse.comprobante.controller.web;


import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;

import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageResponse;
import com.tci.pse.comprobante.core.util.Util;
import com.tci.pse.comprobante.core.util.Validator;
import com.tci.pse.comprobante.service.ComprobantesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
	public HttpEntity<ComprobanteCola> enviarNuevaColaDeclaracion(@RequestBody ComprobanteCola comprobanteCola) {

			try {

			comprobantesService.enviarColaDeclareAux(comprobanteCola);

			LOGGER.info("comprobanteCola :{}" , comprobanteCola);

		} catch (Exception e) {
			LOGGER.error("ocurrio un error :{} ",e);

		}
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/transmitirComprobante")
	public ResponseEntity<?> getEnviarStorage(@Valid @RequestBody ComprobanteStorageRequest json, BindingResult result) {
		//return  comprobantesService.enviarStorage(json);
		Map<String, Object> response = new HashMap<>();
		String link;
		ComprobanteStorageResponse comprobanteStorageResponse;

		if(Validator.validateRequest(result).size()>0){
			return new ResponseEntity<Map<String, Object>>(Validator.validateRequest(result), HttpStatus.BAD_REQUEST);
		};

		try {

			link=comprobantesService.enviarStorage(json);

		}catch (Exception ex){
			response.put("mensaje", "Error al enviar xml al storage");
			response.put("error", ex.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(link, HttpStatus.OK);
	}


}

