show databases;
use bddReto1Grupo2;
SET SQL_SAFE_UPDATES=0;		-- Desactivar modo seguro de las tablas 


/*****************************
CREACION DE TRIGGERS RETO1 GRUPO2
*****************************/ 

/*		TRIGGER DEPARTAMENTO		*/
drop trigger if exists añadirDepartamento;
drop trigger if exists añadirProyecto;

DELIMITER $$
CREATE TRIGGER añadirDepartamento AFTER INSERT ON departamento
FOR EACH ROW 
BEGIN

	declare codigoDep int(10);
    set codigoDep = intIdDepartamento;
    
    INSERT INTO dept_proyecto values (codigoDep);

end ; $$
DELIMITER ;

insert into departamento (vchNombre,doublePresupuesto) values 
("Diseño",100.22),
("Marketing",100.22);

select * from dept_proyecto;
select * from departamento;
delete from departamento;

DELIMITER $$
CREATE TRIGGER añadirProyecto BEFORE INSERT ON proyecto
  FOR EACH ROW BEGIN
    INSERT INTO dept_proyecto SET intIdProyecto = NEW.a1;
  END
end ;
DELIMITER ;
