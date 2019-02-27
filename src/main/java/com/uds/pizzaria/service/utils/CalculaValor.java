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
public class CalculaValor implements PizzaRegrasValor {

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Override
    public Long calculaValorTotal(Pizza pizza) {
        int valorAddAdicionais, valorTamanho = 0;

        valorTamanho = calculaValorDoTamanho(pizza);
        valorAddAdicionais = calculaValorAdicionalDosAdicionais(pizza);

        return new Long(valorTamanho + valorAddAdicionais);
    }

    private int calculaValorDoTamanho(Pizza pizza) {
        Tamanho tamanho = tamanhoRepository.findOne(pizza.getTamanho().getId());

        return tamanho.getValor();
    }

    private int calculaValorAdicionalDosAdicionais(Pizza pizza) {
        int valorAdicional = 0;
        Set<Adicional> adicionalList = pizza.getAdicionais();

        for (Adicional adicionalCurrent : adicionalList) {
            Adicional adicional = adicionalRepository.findOne(adicionalCurrent.getId());
            valorAdicional += adicional.getValor();
        }

        return valorAdicional;
    }

}
