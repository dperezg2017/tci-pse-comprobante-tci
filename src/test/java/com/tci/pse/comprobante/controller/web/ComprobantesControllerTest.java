package com.tci.pse.comprobante.controller.web;


import com.tci.pse.comprobante.config.SenderConfiguration;
import com.tci.pse.comprobante.config.StorageProperties;
import com.tci.pse.comprobante.controller.web.dto.ComprobanteStorageRequest;
import com.tci.pse.comprobante.service.ComprobantesService;
import com.tci.pse.comprobante.service.ComprobantesServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComprobantesController.class)
@ContextConfiguration(classes = {ComprobantesControllerTest.Configuration.class})
public class ComprobantesControllerTest {

	public static class Configuration {

		@Bean
		public ComprobantesService comprobantesService(SenderConfiguration.PubSubComprobanteDeclareGateway pubSubTemplate, StorageProperties storageProperties){
			return new ComprobantesServiceImpl(pubSubTemplate,storageProperties);
		}

	}

	@MockBean
	private StorageProperties storageProperties;

	@MockBean
	private SenderConfiguration.PubSubComprobanteDeclareGateway pubSubTemplate;

	@Autowired
	private ComprobantesService comprobantesService;

	@Test
	public void save_inStorage_ok(){
		// Preparing data
		String msj = "Test storage";
		ComprobanteStorageRequest comprobanteStorageRequest = new ComprobanteStorageRequest();

		comprobanteStorageRequest.setIdTransaccion(123456L);
		comprobanteStorageRequest.setNombreDocumento("20112811094-03-B100-1451.zip");
		comprobanteStorageRequest.setXmlZip(msj.getBytes(StandardCharsets.UTF_8));

		// Mocks & Stubs configuration
		// Business logic execution
		assertThrows(RuntimeException.class,()->{
			comprobantesService.enviarStorage(comprobanteStorageRequest);
		});

		// Validating mocks behaviour
		// Validating results
	}




}

