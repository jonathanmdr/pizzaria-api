package com.uds.pizzaria.service.utils;

import com.uds.pizzaria.model.Adicional;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.model.Sabor;
import com.uds.pizzaria.model.Tamanho;
import com.uds.pizzaria.repository.AdicionalRepository;
import com.uds.pizzaria.repository.SaborRepository;
import com.uds.pizzaria.repository.TamanhoRepository;
import com.uds.pizzaria.service.pizza.PizzaRegrasTempo;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculaTempo implements PizzaRegrasTempo {

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Override
    public Long calculaTempoTotal(Pizza pizza) {
        Long tempoAddSabor, tempoAddAdicionais, tempoTamanho = new Long(0);

        tempoTamanho = calculaTempoDoTamanho(pizza);
        tempoAddSabor = calculaTempoAdicionalDoSabor(pizza);
        tempoAddAdicionais = calculaTempoAdicionalDosAdicionais(pizza);

        return tempoTamanho + tempoAddSabor + tempoAddAdicionais;
    }

    private Long calculaTempoDoTamanho(Pizza pizza) {
        Tamanho tamanho = tamanhoRepository.findOne(pizza.getTamanho().getId());

        return tamanho.getTempo();
    }

    private Long calculaTempoAdicionalDoSabor(Pizza pizza) {
        Sabor sabor = saborRepository.findOne(pizza.getSabor().getId());

        return sabor.getTempo();
    }

    private Long calculaTempoAdicionalDosAdicionais(Pizza pizza) {
        Long tempoAdicional = new Long(0);
        Set<Adicional> adicionalList = pizza.getAdicionais();

        for (Adicional adicionalCurrent : adicionalList) {
            Adicional adicional = adicionalRepository.findOne(adicionalCurrent.getId());
            tempoAdicional += adicional.getTempo();
        }

        return tempoAdicional;
    }

}
