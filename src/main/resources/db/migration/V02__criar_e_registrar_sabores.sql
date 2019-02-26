CREATE TABLE sabor(
  id BIGSERIAL PRIMARY KEY,
  descricao VARCHAR(30),
  tempo INTEGER
);

INSERT INTO sabor(descricao, tempo) VALUES ('Calabresa', 0),
                                           ('Marguerita', 0),
                                           ('Portuguesa', 5);