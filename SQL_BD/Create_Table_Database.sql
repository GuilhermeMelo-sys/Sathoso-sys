Create Database pizzaria;dba

use pizzaria;

SET SQL_SAFE_UPDATES = 0;

CREATE TABLE IF NOT EXISTS pizza(
	id int not null, 
    sabor varchar(60),
    tipo_pizza int,
    valor decimal(10, 2)
); 
CREATE TABLE IF NOT EXISTS endereco(
	id int not null primary key,
    cidade varchar(40),
    logradouro varchar(60),
    bairro varchar(40)
);
CREATE TABLE IF NOT EXISTS pedido(
	id int not null primary key,
    id_endereco int,
    valor_final decimal(10, 2),
    estado bool
);
drop table pedido;
CREATE TABLE IF NOT EXISTS pedidoPizza(
	id int not null primary key,
    id_pedido int,
    id_pizza int,
    id_cobertura int,
    quantidade_pizza int,
    tipo_pizza int
);
drop table pedidoPizza;
CREATE TABLE IF NOT EXISTS cobertura(
	id int not null primary key,
    nome varchar(40),
    valor decimal
);

delete from pedidoPizza;
delete from pedido;
select*from pizza;
select*from pedido;
select*from pedidoPizza;

SELECT*FROM pedidoPizza WHERE and id = ?;