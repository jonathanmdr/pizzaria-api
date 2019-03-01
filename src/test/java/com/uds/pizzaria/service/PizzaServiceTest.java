package com.uds.pizzaria.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.service.utils.CalculaTempoService;
import com.uds.pizzaria.service.utils.CalculaValorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PizzaServiceTest {

    private PizzaService subject;

    private static Long VALOR_TESTE;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private CalculaTempoService calculaTempoService;

    @Mock
    private CalculaValorService calculaValorService;

    @Before
    public void before() {
        subject = new PizzaService(pizzaRepository, calculaTempoService, calculaValorService);
        VALOR_TESTE = new Long(10);
    }

    @Test
    public void save() {
        Pizza pizza = mock(Pizza.class);

        when(calculaTempoService.calculaTempoTotal(pizza)).thenReturn(VALOR_TESTE);
        when(calculaValorService.calculaValorTotal(pizza)).thenReturn(VALOR_TESTE);

        when(pizzaRepository.save(pizza)).thenReturn(mock(Pizza.class));
    }

}
