package com.uds.pizzaria.repository.projection;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumoPizza {

    private Long id;
    private String tamanho;
    private String sabor;
    private List<String> adicionais;
    private Long valor;
    private Long tempo;

}
