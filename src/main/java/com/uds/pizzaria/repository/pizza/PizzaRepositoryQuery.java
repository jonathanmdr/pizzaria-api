package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.repository.projection.ResumoPizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PizzaRepositoryQuery {

    Page<ResumoPizza> findAllResumo(Pageable pageable);

    ResumoPizza findOneResumo(Long id);

}
