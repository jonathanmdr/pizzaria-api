package com.uds.pizzaria.service;

import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.service.utils.CalculaTempo;
import com.uds.pizzaria.service.utils.CalculaValor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private CalculaTempo calculaTempo;

    @Autowired
    private CalculaValor calculaValor;

    public Pizza save(Pizza pizza) {
        pizza.setTempo(calculaTempo.calculaTempoTotal(pizza));
        pizza.setValor(calculaValor.calculaValorTotal(pizza));

        return pizzaRepository.save(pizza);
    }

}
