use wordpress;
select * from usuarios;
select * from empleados;
select concat(e.vchNombre,' ',e.vchApellido1,' ',e.vchApellido2)as 'NombreCompleto',  e.vchDni, u.vchNombreUser, u.dateFechaActual
FROM usuarios u INNER JOIN empleados e on u.intIdUser=e.intIdUser where u.intIdUser= 2;

insert into gastos values() 