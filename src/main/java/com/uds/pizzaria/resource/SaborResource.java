package com.uds.pizzaria.resource;

import com.uds.pizzaria.model.Sabor;
import com.uds.pizzaria.repository.SaborRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sabores")
public class SaborResource {

    @Autowired
    private SaborRepository saborRepository;

    @GetMapping
    public List<Sabor> findAll() {
        return saborRepository.findAll();
    }

}
