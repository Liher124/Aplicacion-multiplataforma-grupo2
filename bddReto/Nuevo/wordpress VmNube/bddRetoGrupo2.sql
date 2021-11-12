/*****************************
CREACION DE BASE DE DATOS DEL RETO1 GRUPO2
*****************************/ 

use wordpress;

/*****************************
CREACION DE TABLAS
*****************************/ 
SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES=0;	

drop table if exists usuarios;
drop table if exists departamento;
drop table if exists gastos;
drop table if exists proyecto;
drop table if exists fichar;

create table usuarios (
	intIdUser int(10) not null auto_increment,
	intIdDepartamento int(10) not null,
    vchNombreUser varchar(50) unique not null,
    vchNombre varchar(20) not null,
    vchApellido1 varchar(20) not null,
    vchApellido2 varchar(20),
	vchEmail varchar(50) unique not null,
    vchDni varchar(9) not null,
    vchPassword varchar(255) not null,
    vchRol varchar(20) not null,
    vchEstado varchar(50),
    dateUltimaConexion datetime,
    primary key (intIdUser),
    foreign key (intIdDepartamento) references departamento(intIdDepartamento)
);

create table gastos (
intIdGasto int(10) auto_increment not null,
intIdProyecto int(10),
intIdDepartamento int(10),
intIdUser int(10)not null,
dateFechaHora datetime,
doubleDistanciaTotalKm double(10,2),
vchMediotransporte varchar(25),
doublePeaje double(7,2),
doubleParking double(7,2),
dateFechaInicio date,
dateFechafin date,
vchPais varchar(50),
vchCiudad varchar(50),
dateDiasTotales int,
doubleTotalFactura double,
primary key (intIdGasto),
constraint FK_intIdProyecto foreign key (intIdProyecto) references proyecto(intIdProyecto),
constraint FK2_intIdDepartamento foreign key (intIdDepartamento) references departamento(intIdDepartamento),
constraint FK_intIdUser foreign key (intIdUser) references usuarios(intIdUser)
);

create table departamento(
intIdDepartamento int(10) auto_increment not null,
vchNombre varchar(50),
primary key(intIdDepartamento)
);

create table proyecto (
intIdProyecto int(10)auto_increment,
vchNombre varchar(50),
dateFechaInicio date,
dateFechaFin date,
primary key(intIdProyecto)
);

create table fichar (
intIdFichar int(10)auto_increment,
intIdUser int(10),
intIdProyecto int(10),
datetimeHoraEntrada time,
datetimeHoraSalida time,
datefecha datetime,
horasDia time,
primary key(intIdFichar),
constraint FK2_intIdUser foreign key (intIdUser) references usuarios(intIdUser),
constraint FK2_intIdProyecto foreign key (intIdProyecto) references proyecto(intIdProyecto)
);
/*****************************
INSERTAR DATOS EN LAS TABLAS
*****************************/ 
SET FOREIGN_KEY_CHECKS=0;
SET SQL_SAFE_UPDATES=0;	

delete from usuarios;
delete from departamento;
delete from gastos;
delete from proyecto;

/*	Insertar datos tabla usuarios */ 
/*ALTER TABLE usuarios AUTO_INCREMENT=1;
insert into usuarios (intIdDepartamento,vchNombreUser,vchNombre,vchApellido1,vchApellido2,vchEmail,vchDni,vchPassword,vchRol,vchEstado,dateUltimaConexion) values 
(1,"Liher124","Liher","Ramoneda","Vicente","liher.ramoneda@maristak.net","7892667B",md5(12345678),"empleado",'activado',now()),
(2,"Admin","Admin","Admin","Admin","admin@maristak.net","xxxxxxxxx",md5(12345678),'administrador','activado',now());
*/select * from usuarios;

/*	Insertar datos tabla gastos */ 
ALTER TABLE gastos AUTO_INCREMENT=1;
ALTER TABLE fichar AUTO_INCREMENT=1;

/*	Insertar datos tabla usuarios */ 
ALTER TABLE departamento AUTO_INCREMENT=1;
insert into departamento (vchNombre) values 
("Desarrollo"),("Finanzas"),("Transporte"),("Informatica"),("Abogados");
select * from departamento;

/*	Insertar datos tabla usuarios */ 
ALTER TABLE proyecto AUTO_INCREMENT=1;
insert into proyecto (vchNombre,dateFechaInicio,dateFechaFin) values 
("Astro",'2018-01-10','2020-04-10'),
("Beta",'2015-11-00','2020-07-19'),
("Omega",'2021-11-11','2025-01-01'),
("Blanco",'2019-01-26','2020-01-26'),
("Aurora",'2011-08-15','2021-12-20');
select * from proyecto;
