package com.uds.pizzaria.repository.projection.query.pizza;

public class ResumoPizzaSQL {

    private static ResumoPizzaSQL instance = null;

    private StringBuilder sb = new StringBuilder();

    private ResumoPizzaSQL() {
    }

    public static ResumoPizzaSQL getInstance() {
        if (instance == null) {
            instance = new ResumoPizzaSQL();
        }

        return instance;
    }

    public final StringBuilder findAllPizzaResumo() {
        sb.delete(0, sb.length());
        sb.append(" SELECT P.ID, S.DESCRICAO AS SABOR, T.DESCRICAO AS TAMANHO, P.VALOR, P.TEMPO ");
        sb.append(" FROM PIZZA P ");
        sb.append(" INNER JOIN TAMANHO T ON(P.IDTAMANHO = T.ID) ");
        sb.append(" INNER JOIN SABOR S ON(P.IDSABOR = S.ID) ");

        return sb;
    }

    public final StringBuilder findOnePizzaResumo() {
        sb.delete(0, sb.length());
        findAllPizzaResumo();
        sb.append(" WHERE P.ID = ?1 ");

        return sb;
    }

}
