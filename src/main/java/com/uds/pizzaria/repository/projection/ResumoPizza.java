package com.uds.pizzaria.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ResumoPizza {

    private Long id;
    private String tamanho;
    private String sabor;
    private Set<String> adicionais;
    private Long valor;
    private Long tempo;

}
