package com.uds.pizzaria.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pizza.class)
public class Pizza_ {

    public static volatile SingularAttribute<Pizza, Long> id;
    public static volatile SingularAttribute<Pizza, Tamanho> tamanho;
    public static volatile SingularAttribute<Pizza, Sabor> sabor;
    public static volatile SingularAttribute<Pizza, Set<Adicional>> adicionais;
    public static volatile SingularAttribute<Pizza, Long> valor;
    public static volatile SingularAttribute<Pizza, Long> tempo;

}
