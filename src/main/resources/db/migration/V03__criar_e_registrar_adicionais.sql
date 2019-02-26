CREATE TABLE adicionais(
  id BIGSERIAL PRIMARY KEY,
  descricao VARCHAR(30),
  valor INTEGER,
  tempo INTEGER
);

INSERT INTO adicionais(descricao, valor, tempo) VALUES ('Extra Bacon', 3, 0),
                                                      ('Sem Cebola', 0, 0),
                                                      ('Borda Recheada', 5, 5);