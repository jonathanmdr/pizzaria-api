package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.model.Adicional_;
import com.uds.pizzaria.model.Pizza;
import com.uds.pizzaria.model.Pizza_;
import com.uds.pizzaria.model.Sabor_;
import com.uds.pizzaria.model.Tamanho_;
import com.uds.pizzaria.repository.projection.ResumoPizza;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
                root.get(Pizza_.adicionais).get(String.valueOf(Adicional_.descricao)),
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
