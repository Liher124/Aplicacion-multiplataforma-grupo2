/*****************************
CREACION DE BASE DE DATOS DEL RETO1 GRUPO2
*****************************/ 

use wordpress;

/*****************************
CREACION DE TABLAS
*****************************/ 

/*		Borrado de tablas 	*/
drop table if exists empleados;
drop table if exists usuarios;
/*drop table if exists tarjeta;*/
drop table if exists departamento;
drop table if exists proyecto;
drop table if exists gastos;
drop table if exists viajes;


/****		TABLAS		****/

create table empleados (
    intIdEmpleado int(10) not null auto_increment,
    intIdUser int(10) not null,
	vchNombre varchar(20) not null,
    vchApellido1 varchar(20) not null,
    vchApellido2 varchar(20),
    vchCorreo varchar(20) not null,
    vchDni varchar(9) not null unique,
    vchEspecialidad varchar(20)not null,
    primary key (intIdEmpleado)
);

create table usuarios (
	intIdUser int(10) not null auto_increment,
	vchEmail varchar(50) unique not null,
    vchNombreUser varchar(50) unique not null,
    vchPassword varchar(255) not null,
    vchRol char(1) not null,
    imagen mediumblob,
    dateUltimaConexion datetime,
    dateFechaActual datetime,
    primary key (intIdUser)
);
select * from usuarios;
/*
create table tarjeta (
	intIdNumeroTarjeta int(10) not null auto_increment,
	intIdEmpleado int(10) not null,
    vchTipo varchar(20) not null,
    primary key (intIdNumeroTarjeta)
);
*/
create table departamento (
	intIdDepartamento int(10) not null auto_increment,
	vchNombre varchar(20) not null,
    primary key (intIdDepartamento)
);

create table proyecto (
	intIdProyecto int(10) not null auto_increment,
	vchNombre varchar(20) not null,
    dateFechaInicio date not null,
    dateFechaFin date not null,
    doublePresupuesto double(10,3) not null,
    primary key (intIdProyecto)
);

create table gastos (
	intIdGasto int(10) not null auto_increment,
    intIdEmpleado int(10) not null,
    intIdDepartamento int(10) not null,
    intIdProyecto int(10) not null,
    intIdViaje int(10),
    dateFechaHoraGasto date not null,
	vchDieta varchar(20),
	intCantidad varchar(10),
    vchMedioTransporte varchar(20),
    doubleKmRecorrido double(5,2),
    doublePeaje double(5,2),
    doubleParking double(5,2),
    doubleCombustible double(5,2),
    primary key (intIdGasto)
);

create table viajes (
    intIdViaje int(10) not null auto_increment,
	vchPais varchar(20),
    vchCiudad varchar(20),
	dateFechaInicio date not null,
    dateFechaFin date not null,
    primary key (intIdViaje)
);

/*****************************
CREACION DE FK
*****************************/ 

/*	FK 	TABLA EMPLEADOS*/ 
alter table empleados
add constraint fk_intIdUser foreign key(intIdUser)
references usuarios (intIdUser);

/*	FK 	TABLA gastos*/ 
alter table gastos
add constraint fk_intIdEmpleado foreign key(intIdEmpleado)
references empleados (intIdEmpleado);

alter table gastos
add constraint fk_intIdProyecto foreign key(intIdProyecto)
references proyecto (intIdProyecto);

alter table gastos
add constraint fk_intIdDepartamento foreign key(intIdDepartamento)
references departamento (intIdDepartamento);

alter table gastos
add constraint fk_intIdViaje foreign key(intIdViaje)
references viajes (intIdViaje);

/*	FK 	TABLA tarjeta
alter table tarjeta
add constraint fk_intIdEmpleado foreign key(intIdEmpleado)
references empleados (intIdEmpleado);*/


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
delete from viajes;



/*	Insertar datos tabla empleados */ 
ALTER TABLE empleados AUTO_INCREMENT=1;
insert into empleados (intIdUser,vchNombre,vchApellido1,vchApellido2,vchCorreo,vchEspecialidad,vchDni) values 
(1,"Pablo","Alvarez","Martinez","pabloAlv@gmail.com","Desarrollador","7892684B"),
(2,"Jose","Manuel","Nevada","joseMan@gmail.com","Abogado","7134684X");

/*	Insertar datos tabla departamento */ 
ALTER TABLE departamento AUTO_INCREMENT=1;
insert into departamento (vchNombre) values 
("Desarrollo"),
("Abogados");


/*	Insertar datos tabla gastos */ 
/*ALTER TABLE gastos AUTO_INCREMENT=1;
insert into gastos (intIdEmpleado,intIdProyecto,intIdDepartamento,intIdViaje,dateFechaHoraGasto,vchDieta,intCantidad,vchMedioTranporte,doubleKmRecorrido,
doublePeaje,doubleParking,doubleCombustible) values 
();
/*

/*	Insertar datos tabla proyecto */ 
ALTER TABLE viajes AUTO_INCREMENT=1;
insert into viajes (vchPais,vchCiudad,dateFechaInicio,dateFechaFin) values 
("España","Bilbao",'2021-08-30','2021-12-30'),
("España","Valencia",'2021-03-30','2021-11-30');


/*	Insertar datos tabla proyecto */ 
ALTER TABLE proyecto AUTO_INCREMENT=1;
insert into proyecto (vchNombre,dateFechaInicio,dateFechaFin,doublePresupuesto) values 
("Alias",'2021-07-30','2021-11-30',19040.20),
("Beta",'2021-01-01','2021-11-30',59010.30);
