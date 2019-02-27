package com.uds.pizzaria.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("pizzaria")
public class PizzariaProperty {

    private String originPermitida = "http://localhost:8080";
}
