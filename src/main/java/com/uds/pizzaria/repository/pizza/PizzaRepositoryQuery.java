package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.repository.projection.ResumoPizza;
import java.util.List;

public interface PizzaRepositoryQuery {

    List<ResumoPizza> findAllResumo();

    ResumoPizza findOneResumo(Long id);

}
