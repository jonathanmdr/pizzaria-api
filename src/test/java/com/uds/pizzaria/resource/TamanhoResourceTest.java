package com.uds.pizzaria.resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uds.pizzaria.repository.TamanhoRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TamanhoResourceTest {

    private TamanhoResource subject;

    @Mock
    private TamanhoRepository tamanhoRepository;

    @Before
    public void before() {
        subject = new TamanhoResource(tamanhoRepository);
    }

    @Test
    public void findAll() {
        subject.findAll();
        when(tamanhoRepository.findAll()).thenReturn(mock(List.class));
        verify(tamanhoRepository).findAll();
    }

}