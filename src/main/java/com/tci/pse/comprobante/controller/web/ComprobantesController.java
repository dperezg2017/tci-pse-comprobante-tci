package com.tci.pse.comprobante.controller.web;


import com.tci.pse.comprobante.controller.web.dto.ComprobanteCola;

import com.tci.pse.comprobante.service.ComprobantesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
	public HttpEntity<ComprobanteCola> enviarNuevaColaDeclaracion(@RequestBody ComprobanteCola comprobanteCola) {

			try {

			comprobantesService.enviarColaDeclareAux(comprobanteCola);

			LOGGER.info("comprobanteCola --> {}" , comprobanteCola);

		} catch (Exception e) {
			LOGGER.error("ocurrio un error {}",e);

		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/transmitirComprobante", method = RequestMethod.POST)
	@ResponseBody
	public String getEnviarStorage(@Valid @ModelAttribute @RequestBody String json) {
		return  comprobantesService.enviarStorage(json);
	}

}

