package com.uds.pizzaria.service.utils;

import com.uds.pizzaria.model.Adicional;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.model.Tamanho;
import com.uds.pizzaria.repository.AdicionalRepository;
import com.uds.pizzaria.repository.TamanhoRepository;
import com.uds.pizzaria.service.pizza.PizzaRegrasValor;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculaValorService implements PizzaRegrasValor {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Override
    public Long calculaValorTotal(Pizza pizza) {
        Long valorAddAdicionais, valorTamanho, total;

        valorTamanho = calculaValorDoTamanho(pizza);
        valorAddAdicionais = calculaValorAdicionalDosAdicionais(pizza);
        total = valorTamanho + valorAddAdicionais;

        return total;
    }

    private Long calculaValorDoTamanho(Pizza pizza) {
        Tamanho tamanho = tamanhoRepository.findOne(pizza.getTamanho().getId());

        return tamanho.getValor();
    }

    private Long calculaValorAdicionalDosAdicionais(Pizza pizza) {
        Long valorAdicional = Long.valueOf(0);
        Set<Adicional> adicionalList = pizza.getAdicionais();

        for (Adicional adicionalCurrent : adicionalList) {
            Adicional adicional = adicionalRepository.findOne(adicionalCurrent.getId());
            valorAdicional += adicional.getValor();
        }

        return valorAdicional;
    }

}
