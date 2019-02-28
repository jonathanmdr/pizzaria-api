package com.uds.pizzaria.resource;

import com.uds.pizzaria.model.Tamanho;
import com.uds.pizzaria.repository.TamanhoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tamanhos")
public class TamanhoResource {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @GetMapping
    public List<Tamanho> findAll() {
        return tamanhoRepository.findAll();
    }

}
