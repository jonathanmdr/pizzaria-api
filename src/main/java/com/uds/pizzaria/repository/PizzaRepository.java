package com.uds.pizzaria.repository;

import com.uds.pizzaria.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Page<Pizza> findAll(Pageable pageable);

}
