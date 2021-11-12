use wordpress;
select * from usuarios;
select * from departamento;
select * from gastos;
select * from proyecto;
select * from fichar;

SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES=0;	

delete from usuarios;
delete from departamento;
delete from gastos;
delete from proyecto;
delete from fichar;