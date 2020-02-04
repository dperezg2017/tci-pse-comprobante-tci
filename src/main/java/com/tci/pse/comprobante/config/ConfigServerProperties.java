package com.tci.pse.comprobante.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//We use @Configuration so that Spring creates a Spring bean in the application context.
@Configuration
@EnableConfigurationProperties({StorageProperties.class})
public class ConfigServerProperties {
}
