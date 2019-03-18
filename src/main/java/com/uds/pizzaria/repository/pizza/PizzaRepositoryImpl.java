package com.uds.pizzaria.repository.pizza;

import com.uds.pizzaria.repository.projection.ResumoPizza;

import com.uds.pizzaria.repository.projection.query.adicional.ResumoAdicionalSQL;
import com.uds.pizzaria.repository.projection.query.pizza.ResumoPizzaSQL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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
    public List<ResumoPizza> findAllResumo() {
        Query query = manager.createNativeQuery(pizzaSQL.findAllPizzaResumo().toString());

        List<?> resultList = query.getResultList();

        List<ResumoPizza> listResumo = new ArrayList<>();

        if (resultList != null && resultList.size() > 0) {
            for (Object value : resultList) {
                ResumoPizza resumo = new ResumoPizza();
                Object[] result = (Object[]) value;

                montaPizzaResumo(resumo, result);
                resumo.setAdicionais(findListaAdicionaisPizzaById(resumo.getId()));

                listResumo.add(resumo);
            }
        }

        return listResumo;
    }

    @Override
    public ResumoPizza findOneResumo(Long id) {
        Query query = manager.createNativeQuery(pizzaSQL.findOnePizzaResumo().toString());
        query.setParameter(1, id);

        Object value = query.getSingleResult();
        Object[] result = (Object[]) value;

        ResumoPizza resumo = new ResumoPizza();

        if (result != null) {
            montaPizzaResumo(resumo, result);
            resumo.setAdicionais(findListaAdicionaisPizzaById(id));
        }

        return resumo;
    }

    private void montaPizzaResumo(ResumoPizza resumo, Object[] result) {
        resumo.setId(new Long(result[0].toString()).longValue());
        resumo.setSabor(result[1].toString());
        resumo.setTamanho(result[2].toString());
        resumo.setValor(new Long(result[3].toString()).longValue());
        resumo.setTempo(new Long(result[4].toString()).longValue());
    }

    private List<String> findListaAdicionaisPizzaById(Long id) {
        Query query1 = manager.createNativeQuery(resumoAdicionalSQL.findAllAdiconaisPizzaByIdResumo().toString());
        query1.setParameter(1, id);

        List<?> resultList1 = query1.getResultList();

        if (resultList1 != null && resultList1.size() > 0) {
            List<String> listAdicional = new ArrayList<>();

            for (Object value1 : resultList1) {
                listAdicional.add(value1.toString());
            }

            return listAdicional;
        }

        return null;
    }

}
