CREATE TABLE transacoes (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ticker varchar(6) NOT NULL,
  preco decimal(19,2) NOT NULL,  
  quantidade int(11) NOT NULL,  
  data date NOT NULL,
  tipo varchar(100) NOT NULL,
  PRIMARY KEY (id)
  ) 