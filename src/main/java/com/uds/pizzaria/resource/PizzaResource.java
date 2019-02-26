package com.uds.pizzaria.resource;

import com.uds.pizzaria.event.RecursoCriadoEvent;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
public class PizzaResource {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findOne(id);

        return pizza != null ? ResponseEntity.ok(pizza) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pizza> save(@Valid @RequestBody Pizza pizza, HttpServletResponse response) {
        Pizza pizzaSalva = pizzaRepository.save(pizza);

        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, pizzaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaSalva);
    }

}
