package com.uds.pizzaria;

import com.uds.pizzaria.config.PizzariaProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PizzariaProperty.class)
public class PizzariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzariaApplication.class, args);
    }

}
