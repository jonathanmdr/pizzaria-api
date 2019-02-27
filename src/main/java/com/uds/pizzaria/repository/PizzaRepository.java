package com.uds.pizzaria.repository;

import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.repository.pizza.PizzaRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long>, PizzaRepositoryQuery {

    Page<Pizza> findAll(Pageable pageable);

}
