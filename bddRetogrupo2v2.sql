/*****************************
CREACION DE BASE DE DATOS DEL RETO1 GRUPO2
*****************************/ 

use wordpress;

/*****************************
CREACION DE TABLAS
*****************************/ 
SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES=0;	

/*		Borrado de tablas 	*/
drop table if exists empleados;
drop table if exists usuarios;
drop table if exists departamento;
drop table if exists proyecto;
drop table if exists gastos;

/****		TABLAS		****/
/* Definitivo sin hacer
create table usuarios (
	intIdUser int(10) not null auto_increment,
	intIdDepartamento int(10) not null,
    vchNombreUser varchar(50) unique not null,
    vchNombre varchar(20) not null,
    vchApellido1 varchar(20) not null,
    vchApellido2 varchar(20),
	vchEmail varchar(50) unique not null,
    vchPassword varchar(255) not null,
    vchRol varchar(20) not null,
    dateUltimaConexion datetime,
    vchEstado varchar(50),
    primary key (intIdUser),
    foreign key (intIdDepartamento) references departamento(intIdDepartamento)
);
*/

create table empleados (
    intIdEmpleado int(10) not null auto_increment,
    intIdUser int(10) not null,
	vchNombre varchar(20) not null,
    vchApellido1 varchar(20) not null,
    vchApellido2 varchar(20),
    vchCorreo varchar(50) not null,
    vchDni varchar(9) not null unique,
    vchEspecialidad varchar(20)not null,
	intidDep int,
	intidGasto int,
    primary key (intIdEmpleado)
);

create table usuarios (
	intIdUser int(10) not null auto_increment,
	vchEmail varchar(50) unique not null,
    vchNombreUser varchar(50) unique not null,
    vchPassword varchar(255) not null,
    vchRol varchar(20) not null,
    imagen longblob,
    dateUltimaConexion datetime,
    vchEstado varchar(50),
    primary key (intIdUser)
);


create table proyecto (
intidpro int,
vchNom varchar(30),
dateFechaInicio date,
dateFechafin date,
doublePresupuesto double(10,2),
PRIMARY KEY(intidpro)
);

create table departamento(
intIdDepartamento int(10),
vchNombre varchar(50),
PRIMARY KEY(intIdDepartamento)
);


create table gastos (
intidGasto int auto_increment PRIMARY KEY,
intidpro int,
intidDep int,
dateFechaHora datetime,
inttotalKm double ,
vchDieta varchar(15),
vchMedtransporte varchar(15),
intPeaje int,
intParking int,
vchCombustible varchar(15),
intdistancia int,
totalfac double,
intidempleado int,
vchpais varchar(30),
vchciudad varchar(30),
dateFechainicio date,
dateFechafin date,
intdifdias int,
intIdUser int(10)
);


/*****************************
CREACION DE FK
*****************************/ 

/*	FK 	TABLA EMPLEADOS*/ 
alter table empleados
add constraint fk_intIdUser foreign key(intIdUser)
references usuarios (intIdUser);

/*	FK 	TABLA gastos*/ 
Alter table gastos
Add constraint fk_gastos FOREIGN KEY (intidempleado) references empleados(intIdEmpleado);

Alter table gastos
Add constraint fk2_gastos FOREIGN KEY (intidpro) references proyecto(intidpro);

Alter table gastos
Add constraint fk3_gastos FOREIGN KEY (intidDep) references departamento(intidDep);

Alter table gastos
Add constraint fk4_gastos FOREIGN KEY (intIdUser) references empleados(intIdUser);
/*****************************
INSERTAR DATOS EN LAS TABLAS
*****************************/ 
SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES=0;	

delete from empleados;
delete from usuarios;
delete from departamento;
delete from gastos;
delete from proyecto;


/*	Insertar datos tabla empleados */ 
ALTER TABLE empleados AUTO_INCREMENT=1;
insert into empleados (intIdUser,vchNombre,vchApellido1,vchApellido2,vchCorreo,vchDni,vchEspecialidad,intidDep,intidGasto) values 
(1,"Liher","Ramoneda","Vicente","liher.ramoneda@maristak.net","7892667B","Desarrollo",1,1),
(2,"Xabier","Parra","Navarro","xabi.parra@maristak.net","9138384X","Transporte",3,1),
(3,"Jon","Herrero","","jon.herrero@gmail.com","7338384O","Abogados",2,1),
(4,"Daniel","Alvarez","Toledo","daniel.alvarez@gmail.com","2137154I","Programacion",5,1),
(5,"Javier","Garcia","Torrel","javier.garcia@gmail.com","9138384L","Desarrollo",1,1);
select * from empleados;


-- TABLA PROYECTO
ALTER TABLE proyecto AUTO_INCREMENT=1;
insert into proyecto (intidpro,vchNom,dateFechaInicio,dateFechafin,doublePresupuesto) VALUES (1,"Aplicacion Android",'2021/10/15','2021/12/20',30000.20);
insert into proyecto (intidpro,vchNom,dateFechaInicio,dateFechafin,doublePresupuesto) VALUES (2,"Aplicacion Web",'2021/10/15','2021/12/20',30000.30);
insert into proyecto (intidpro,vchNom,dateFechaInicio,dateFechafin,doublePresupuesto) VALUES (3,"Delta",'2021/10/15','2021/12/20',30000.30);
insert into proyecto (intidpro,vchNom,dateFechaInicio,dateFechafin,doublePresupuesto) VALUES (4,"Creacion Base de datos",'2021/10/15','2021/12/20',30000.30);
insert into proyecto (intidpro,vchNom,dateFechaInicio,dateFechafin,doublePresupuesto) VALUES (5,"Gestion Empresa",'2021/10/15','2021/12/20',30000.30);
select * from proyecto;

-- TABLA DEPARTAMENTO
ALTER TABLE departamento AUTO_INCREMENT=1;
insert into departamento (intidDep,vchNom) VALUES (1,"Desarrollo");
insert into departamento (intidDep,vchNom) VALUES (2,"Abogados");
insert into departamento (intidDep,vchNom) VALUES (3,"Transporte");
insert into departamento (intidDep,vchNom) VALUES (4,"Mantenimiento");
insert into departamento (intidDep,vchNom) VALUES (5,"Programacion");
select * from departamento;
