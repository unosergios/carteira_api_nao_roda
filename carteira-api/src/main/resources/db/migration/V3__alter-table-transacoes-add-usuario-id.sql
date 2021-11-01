alter TABLE transacoes add column usuario_id bigint(20) NOT NULL;
alter table transacoes add foreign key (usuario_id) references usuarios(id);
 