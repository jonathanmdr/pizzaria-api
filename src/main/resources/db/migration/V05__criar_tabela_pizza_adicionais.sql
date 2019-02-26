CREATE TABLE pizza_adicionais(
  idpizza INTEGER NOT NULL PRIMARY KEY,
  idadicional INTEGER NOT NULL,
CONSTRAINT fk_pizzaadicionais_pizza FOREIGN KEY(idpizza) REFERENCES pizza(id),
CONSTRAINT fk_pizzaadicionais_adicionais FOREIGN KEY(idadicional) REFERENCES adicionais(id)
);