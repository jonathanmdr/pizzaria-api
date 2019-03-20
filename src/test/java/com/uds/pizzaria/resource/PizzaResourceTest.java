package com.uds.pizzaria.resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uds.pizzaria.event.RecursoCriadoEvent;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.service.PizzaService;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RunWith(MockitoJUnitRunner.class)
public class PizzaResourceTest {

    private PizzaResource subject;

    private static Long ID_TESTE;

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private PizzaService pizzaService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Before
    public void before() {
        subject = new PizzaResource(pizzaRepository, pizzaService, eventPublisher);
        ID_TESTE = new Long(1);
    }

    @Test
    public void findAll() {
        Pageable pageable = mock(Pageable.class);

        subject.findAll(pageable);
        when(pizzaRepository.findAll(pageable)).thenReturn(mock(Page.class));
        verify(pizzaRepository).findAll(pageable);
    }

    @Test
    public void findAllResumo() {
        Pageable pageable = mock(Pageable.class);

        subject.findAllResumo(pageable);
        when(pizzaRepository.findAllResumo(pageable)).thenReturn(mock(Page.class));
        verify(pizzaRepository).findAllResumo(pageable);
    }

    @Test
    public void findById() {
        subject.findById(ID_TESTE);
        when(pizzaRepository.findOne(ID_TESTE)).thenReturn(mock(Pizza.class));
        verify(pizzaRepository).findOne(ID_TESTE);
    }

    @Test
    public void save() {
        Pizza pizza = mock(Pizza.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        pizzaService.save(pizza);

        eventPublisher.publishEvent(new RecursoCriadoEvent(mock(PizzaResource.class), response, pizza.getId()));

        when(pizzaService.save(pizza)).thenReturn(mock(Pizza.class));
        verify(pizzaService).save(pizza);
    }

}
