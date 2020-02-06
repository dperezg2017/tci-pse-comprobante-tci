package com.tci.pse.comprobante.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        PubSubProperties.class,
        StorageProperties.class})
public class ConfigServerProperties {
}
