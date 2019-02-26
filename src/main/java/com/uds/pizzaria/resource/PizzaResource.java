package com.uds.pizzaria.resource;

import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaResource {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

}
