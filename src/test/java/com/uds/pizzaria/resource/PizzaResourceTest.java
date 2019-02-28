package com.uds.pizzaria.resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uds.pizzaria.repository.PizzaRepository;
import com.uds.pizzaria.service.PizzaService;
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

    @Mock
    private PizzaRepository pizzaRepository;

    @Mock
    private PizzaService pizzaService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Before
    public void before() {
        subject = new PizzaResource(pizzaRepository, pizzaService, eventPublisher);
    }

    @Test
    public void findAll() {
        Pageable pageable = mock(Pageable.class);

        subject.findAll(pageable);
        when(pizzaRepository.findAll(pageable)).thenReturn(mock(Page.class));
        verify(pizzaRepository).findAll(pageable);
    }

}
