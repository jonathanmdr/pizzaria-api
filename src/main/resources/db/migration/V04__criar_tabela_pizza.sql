CREATE TABLE pizza(
  id BIGSERIAL PRIMARY KEY,
  idtamanho INTEGER NOT NULL,
  idsabor INTEGER NOT NULL,
  tempo INTEGER,
  valor INTEGER,
CONSTRAINT fk_pizza_tamanho FOREIGN KEY(idtamanho) REFERENCES tamanho(id),
CONSTRAINT fk_pizza_sabor FOREIGN KEY(idsabor) REFERENCES sabor(id)
);