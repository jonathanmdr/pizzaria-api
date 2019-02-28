package com.uds.pizzaria.resource;

import com.uds.pizzaria.model.Adicional;
import com.uds.pizzaria.repository.AdicionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/adicionais")
public class AdicionalResource {

    @Autowired
    private AdicionalRepository adicionalRepository;

    @GetMapping
    public List<Adicional> findAll() {
        return adicionalRepository.findAll();
    }

}
