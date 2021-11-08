/*****************************
CREACION DE BASE DE DATOS DEL RETO1 GRUPO2
*****************************/ 

use wordpress;

/*****************************
CREACION DE TABLAS
*****************************/ 

drop table if exists usuarios;
drop table if exists departamento;
drop table if exists gastos;

create table usuarios (
	intIdUser int(10) not null auto_increment,
	intIdDepartamento int(10) not null,
    vchNombreUser varchar(50) unique not null,
    vchNombre varchar(20) not null,
    vchApellido1 varchar(20) not null,
    vchApellido2 varchar(20),
	vchEmail varchar(50) unique not null,
    vchDni varchar(8) not null,
    vchPassword varchar(255) not null,
    vchRol varchar(20) not null,
    dateUltimaConexion datetime,
    vchEstado varchar(50),
    primary key (intIdUser),
    foreign key (intIdDepartamento) references departamento(intIdDepartamento)
);

create table gastos (
intIdGasto int(10) auto_increment not null,
intIdProyecto int(10)not null,
intIdDieta int(10)not null,
intIdUser int(10)not null,
dateFechaHora datetime,
doubleDistanciaTotalKm double(10,2),
vchMediotransporte varchar(25),
doublePeaje double(7,2),
doubleParking double(7,2),
vchCombustible varchar(15),
doubleTotalFactura double,
primary key (intIdGasto),
foreign key (intIdProyecto) references proyecto(intIdDepartamento),
foreign key (intIdDieta) references departamento(intIdDepartamento),
foreign key (intIdUser) references departamento(intIdDepartamento)
);

create table departamento(
intIdDepartamento int(10) auto_increment not null,
vchNombre varchar(50),
primary key(intIdDepartamento)
);

create table proyecto (
intIdProyecto int(10) auto_increment not null,
vchNombre varchar(50),
primary key(intIdProyecto)
);

create table dieta (
intIdDieta int(10),
vchNombreTarjeta varchar(50),
intCantidad int(10),
dateFechaInicio date,
dateFechafin date,
dateDiasTotales int,
primary key(intIdDieta)
);

create table proyecto (
intIdProyecto int(10),
vchNom varchar(30),
dateFechaInicio date,
dateFechafin date,
doublePresupuesto double(10,2),
primary key(intIdProyecto)
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
ALTER TABLE usuarios AUTO_INCREMENT=1;
insert into usuarios (intIdDepartamento,vchNombreUser,vchNombre,vchApellido1,vchApellido2,vchEmail,vchDni,vchEspecialidad,intidDep,intidGasto) values 
(1,"Liher124","Liher","Ramoneda","Vicente","liher.ramoneda@maristak.net","7892667B","Desarrollo",1,1),
(2,"Xabier","Parra","Navarro","xabi.parra@maristak.net","9138384X","Transporte",3,1),
(3,"Jon","Herrero","","jon.herrero@gmail.com","7338384O","Abogados",2,1),
(4,"Daniel","Alvarez","Toledo","daniel.alvarez@gmail.com","2137154I","Programacion",5,1),
(5,"Javier","Garcia","Torrel","javier.garcia@gmail.com","9138384L","Desarrollo",1,1);
select * from empleados;