package com.uds.pizzaria.repository.projection.query.adicional;

public class ResumoAdicionalSQL {

    private static ResumoAdicionalSQL instance = null;

    private StringBuilder sb = new StringBuilder();

    private ResumoAdicionalSQL() {
    }

    public static ResumoAdicionalSQL getInstance() {
        if (instance == null) {
            instance = new ResumoAdicionalSQL();
        }

        return instance;
    }

    public final StringBuilder findAllAdiconaisPizzaByIdResumo() {
        sb.delete(0, sb.length());
        sb.append(" SELECT A.DESCRICAO ");
        sb.append(" FROM PIZZA P ");
        sb.append(" LEFT JOIN PIZZA_ADICIONAIS PA ON(P.ID = PA.IDPIZZA) ");
        sb.append(" LEFT JOIN ADICIONAIS A ON(PA.IDADICIONAL = A.ID) ");
        sb.append(" WHERE P.ID = ?1 ");

        return sb;
    }

}
