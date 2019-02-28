package com.uds.pizzaria.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumoPizza {

    private Long id;
    private String tamanho;
    private String sabor;
    private Long valor;
    private Long tempo;

}
