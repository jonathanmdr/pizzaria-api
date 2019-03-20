package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.repository.projection.ResumoPizza;

import com.uds.pizzaria.repository.projection.query.adicional.ResumoAdicionalSQL;
import com.uds.pizzaria.repository.projection.query.pizza.ResumoPizzaSQL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PizzaRepositoryImpl implements PizzaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    private final ResumoPizzaSQL pizzaSQL;
    private final ResumoAdicionalSQL resumoAdicionalSQL;

    public PizzaRepositoryImpl() {
        this.pizzaSQL = ResumoPizzaSQL.getInstance();
        this.resumoAdicionalSQL = ResumoAdicionalSQL.getInstance();
    }

    @Override
    public Page<ResumoPizza> findAllResumo(Pageable pageable) {
        Query query = manager.createNativeQuery(pizzaSQL.findAllPizzaResumo().toString());

        List<?> resultList = query.getResultList();
        adicionarRestricoesDePaginacao(query, pageable);

        List<ResumoPizza> listResumo = new ArrayList<>();

        if (resultList != null && resultList.size() > 0) {
            for (Object value : resultList) {
                ResumoPizza resumo = new ResumoPizza();
                Object[] result = (Object[]) value;

                montaPizzaResumo(resumo, result);
                resumo.setAdicionais(findListaAdicionaisByPizzaId(resumo.getId()));

                listResumo.add(resumo);
            }
        }

        return new PageImpl<>(listResumo, pageable, total(resultList));
    }

    @Override
    public ResumoPizza findOneResumo(Long id) {
        Query query = manager.createNativeQuery(pizzaSQL.findOnePizzaResumo().toString());
        query.setParameter(1, id);

        Object value = query.getSingleResult();
        Object[] result = (Object[]) value;

        ResumoPizza resumo = null;

        if (result != null) {
            resumo = new ResumoPizza();

            montaPizzaResumo(resumo, result);
            resumo.setAdicionais(findListaAdicionaisByPizzaId(id));
        }

        return resumo;
    }

    private void montaPizzaResumo(ResumoPizza resumo, Object[] result) {
        resumo.setId(Long.parseLong(result[0].toString()));
        resumo.setSabor(result[1].toString());
        resumo.setTamanho(result[2].toString());
        resumo.setValor(Long.parseLong(result[3].toString()));
        resumo.setTempo(Long.parseLong(result[4].toString()));
    }

    private List<String> findListaAdicionaisByPizzaId(Long id) {
        Query query = manager.createNativeQuery(resumoAdicionalSQL.findAllAdiconaisPizzaByIdResumo().toString());
        query.setParameter(1, id);

        List<?> resultList = query.getResultList();
        List<String> listAdicional = null;

        if (resultList != null && resultList.size() > 0) {
            listAdicional = new ArrayList<>();

            for (Object value : resultList) {
                if (value != null) {
                    listAdicional.add(value.toString());
                }
            }
        }

        return listAdicional;
    }

    private void adicionarRestricoesDePaginacao(Query query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(List<?> resultList) {
        return Long.valueOf(resultList.size());
    }

}
