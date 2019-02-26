CREATE TABLE tamanho (
  id BIGSERIAL PRIMARY KEY,
  descricao VARCHAR(20),
  valor INTEGER,
  tempo INTEGER
);

INSERT INTO tamanho(descricao, valor, tempo) VALUES ('PEQUENO', 20, 15),
                                                    ('MEDIO', 30, 20),
                                                    ('GRANDE', 40, 25);