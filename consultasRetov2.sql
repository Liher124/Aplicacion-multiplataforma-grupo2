use wordpress;
select * from usuarios;
select * from empleados;
select * from gastos;
delete from gastos;

-- ***********		USUARIO ADMINISTRADOR **************
-- INFORMACION DE PERFIL DEL USUARIO
select concat(e.vchNombre,' ',e.vchApellido1,' ',e.vchApellido2)as 'NombreCompleto',  e.vchDni, u.vchNombreUser, u.dateUltimaConexion 
from usuarios u INNER JOIN empleados e on u.intIdUser=e.intIdUser where u.intIdUser= 2;


insert into gastos (intIdEmpleado, intIdDepartamento, intIdProyecto, intIdViaje, dateFechaHoraGasto, vchDieta, intCantidad, vchMedioTransporte, doubleKmRecorrido, doublePeaje, doubleParking, doubleCombustible) 
values (1, 1, 1, 1, CURRENT_TIMESTAMP(), "", 10, "Coche", 109, 1.1, 10, 100);


select * from usuarios;
select * from empleados;
select * from gastos;

delete from usuarios where intIdUser =5;
