package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.model.*;
import com.uds.pizzaria.repository.projection.ResumoPizza;

import javax.persistence.*;
import javax.persistence.criteria.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PizzaRepositoryImpl implements PizzaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ResumoPizza> resumir(Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoPizza> criteria = builder.createQuery(ResumoPizza.class);

        Root<Pizza> root = criteria.from(Pizza.class);

        criteria.select(builder.construct(ResumoPizza.class,
                root.get(Pizza_.id),
                root.get(Pizza_.tamanho).get(Tamanho_.descricao),
                root.get(Pizza_.sabor).get(Sabor_.descricao),
                root.get(Pizza_.valor),
                root.get(Pizza_.tempo)));

        TypedQuery<ResumoPizza> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total());
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Pizza> root = criteria.from(Pizza.class);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

}
