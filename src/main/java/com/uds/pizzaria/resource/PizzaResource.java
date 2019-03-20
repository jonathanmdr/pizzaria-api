package com.uds.pizzaria.resource;

import com.uds.pizzaria.event.RecursoCriadoEvent;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;

import com.uds.pizzaria.repository.projection.ResumoPizza;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.uds.pizzaria.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/pizzas")
public class PizzaResource {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping
    public Page<Pizza> findAll(Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }

    @GetMapping(params = "resumo")
    public Page<ResumoPizza> findAllResumo(Pageable pageable) {
        return pizzaRepository.findAllResumo(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findById(@PathVariable Long id) {
        Pizza pizza = pizzaRepository.findOne(id);

        return pizza != null ? ResponseEntity.ok(pizza) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}", params = "resumo")
    public ResponseEntity<ResumoPizza> findByIdResumo(@PathVariable Long id) {
        ResumoPizza resumo = pizzaRepository.findOneResumo(id);

        return resumo != null ? ResponseEntity.ok(resumo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pizza> save(@Valid @RequestBody Pizza pizza, HttpServletResponse response) {
        Pizza pizzaSalva = pizzaService.save(pizza);

        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, pizzaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaSalva);
    }

}
