package com.uds.pizzaria.service;

import com.uds.pizzaria.model.Adicional;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.model.Sabor;
import com.uds.pizzaria.model.Tamanho;
import com.uds.pizzaria.repository.AdicionalRepository;
import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.repository.SaborRepository;
import com.uds.pizzaria.repository.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private SaborRepository saborRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private AdicionalRepository adicionalRepository;

    public Pizza save(Pizza pizza) {
        pizza.setTempo(new Long(calculaTempoTotal(pizza)));
        pizza.setValor(new Long(calculaValorTotal(pizza)));

        return pizzaRepository.save(pizza);
    }

    private int calculaTempoTotal(Pizza pizza) {
        int tempoAddSabor, tempoAddAdicionais, tempoTamanho = 0;

        tempoTamanho = calculaTempoDoTamanho(pizza);
        tempoAddSabor = calculaTempoAdicionalDoSabor(pizza);
        tempoAddAdicionais = calculaTempoAdicionalDosAdicionais(pizza);

        return tempoTamanho + tempoAddSabor + tempoAddAdicionais;
    }

    private int calculaTempoDoTamanho(Pizza pizza) {
        Tamanho tamanho = tamanhoRepository.findOne(pizza.getTamanho().getId());

        return tamanho.getTempo();
    }

    private int calculaTempoAdicionalDoSabor(Pizza pizza) {
        Sabor sabor = saborRepository.findOne(pizza.getSabor().getId());

        return sabor.getTempo();
    }

    private int calculaTempoAdicionalDosAdicionais(Pizza pizza) {
        int tempoAdicional = 0;
        Set<Adicional> adicionalList = pizza.getAdicionais();

        for (Adicional adicionalCurrent : adicionalList) {
            Adicional adicional = adicionalRepository.findOne(adicionalCurrent.getId());
            tempoAdicional += adicional.getTempo();
        }

        return tempoAdicional;
    }

    private int calculaValorTotal(Pizza pizza) {
        int valorAddAdicionais, valorTamanho = 0;

        valorTamanho = calculaValorDoTamanho(pizza);
        valorAddAdicionais = calculaValorAdicionalDosAdicionais(pizza);

        return valorTamanho + valorAddAdicionais;
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
