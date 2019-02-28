package com.uds.pizzaria.resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uds.pizzaria.repository.SaborRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaborResourceTest {

    private SaborResource subject;

    @Mock
    private SaborRepository saborRepository;

    @Before
    public void before() {
        subject = new SaborResource(saborRepository);
    }

    @Test
    public void findAll() {
        subject.findAll();
        when(saborRepository.findAll()).thenReturn(mock(List.class));
        verify(saborRepository).findAll();
    }

}
