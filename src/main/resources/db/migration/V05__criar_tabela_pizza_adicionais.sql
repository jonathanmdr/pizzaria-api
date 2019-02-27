CREATE TABLE pizza_adicionais(
  idpizza INTEGER NOT NULL,
  idadicional INTEGER NOT NULL,
CONSTRAINT pk_adicionais PRIMARY KEY(idpizza, idadicional),
CONSTRAINT fk_pizzaadicionais_pizza FOREIGN KEY(idpizza) REFERENCES pizza(id),
CONSTRAINT fk_pizzaadicionais_adicionais FOREIGN KEY(idadicional) REFERENCES adicionais(id)
);