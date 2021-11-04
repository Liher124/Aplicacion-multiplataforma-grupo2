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
    vchCorreo varchar(50) not null,
    vchDni varchar(9) not null unique,
    vchEspecialidad varchar(20)not null,
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
	intIdGasto int(5) not null auto_increment,
    intIdEmpleado int(5),
    intIdDepartamento int(5),
    intIdProyecto int(5),
    intIdViaje int(5),
    dateFechaHoraGasto datetime,
	vchDieta varchar(50),
	doubleCantidadTotalGasto double(10,2),
    vchMedioTransporte varchar(50),
    doubleKmRecorrido double(10,2),
    doublePeaje double(6,2),
    doubleParking double(6,2),
    doubleCombustible double(6,2),
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
(1,"Liher","Ramoneda","Vicente","liher.ramoneda@maristak.net","Desarrollador","7892667B"),
(2,"Liher","Ramoneda","Vicente","liher.ramoneda@gmail.com","Informatico","9138384X"),
(3,"Jon","Herrero","Nevada","jon.herrero@maristak.net","Abogado","7634684F"),
(4,"Aitor","Manuel","","aitor.manuel@gmail.com","Desarrollador","3464684A"),
(5,"Xabi","Parra","Navarro","xabi.parra@gmail.com","Abogado","7134684J"),
(6,"Jose","Manuel","Nevada","joseMan@gmail.com","Informatico","7134684V");
select * from empleados;

/*	Insertar datos tabla departamento */ 
ALTER TABLE departamento AUTO_INCREMENT=1;
insert into departamento (vchNombre) values 
("Desarrollo"),("Abogados"),("Mantenimiento"),("Transporte"),("Programacion");
select * from departamento;

/*	Insertar datos tabla gastos */ 
/*ALTER TABLE gastos AUTO_INCREMENT=1;
insert into gastos (intIdEmpleado,intIdProyecto,intIdDepartamento,intIdViaje,dateFechaHoraGasto,vchDieta,intCantidad,vchMedioTranporte,doubleKmRecorrido,
doublePeaje,doubleParking,doubleCombustible) values 
();
/*

/*	Insertar datos tabla viaje */ 
ALTER TABLE viajes AUTO_INCREMENT=1;
insert into viajes (vchPais,vchCiudad,dateFechaInicio,dateFechaFin) values 
("España","Bilbao",'2021-08-30','2021-12-30'),
("España","Valencia",'2021-03-30','2021-11-30');


/*	Insertar datos tabla proyecto */ 
ALTER TABLE proyecto AUTO_INCREMENT=1;
insert into proyecto (vchNombre,dateFechaInicio,dateFechaFin,doublePresupuesto) values 
("Ala",'2021-07-30','2021-11-30',19040.20),
("Beta",'2021-01-01','2021-11-30',59010.30),
("Delta",'2021-01-01','2021-11-30',59010.30),
("Gamma",'2021-01-01','2021-11-30',59010.30);
select * from proyecto;