package com.uds.pizzaria.service;

import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.service.utils.CalculaTempoService;
import com.uds.pizzaria.service.utils.CalculaValorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private CalculaTempoService calculaTempoService;

    @Autowired
    private CalculaValorService calculaValorService;

    public Pizza save(Pizza pizza) {
        pizza.setTempo(calculaTempoService.calculaTempoTotal(pizza));
        pizza.setValor(calculaValorService.calculaValorTotal(pizza));

        return pizzaRepository.save(pizza);
    }

}
