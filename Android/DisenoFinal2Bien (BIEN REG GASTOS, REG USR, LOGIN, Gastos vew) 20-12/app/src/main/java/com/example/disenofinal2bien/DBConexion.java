package com.example.disenofinal2bien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.disenofinal2bien.Interfaz.Entidades.Departamento;
import com.example.disenofinal2bien.Interfaz.Entidades.Proyectos;

import java.util.ArrayList;
import java.util.List;

// archvo donde se gestiona tod0 sobre la BBDD, crear la BBDD, sentencia de inserts,
// devoluciones de datos, etc...
public class DBConexion extends SQLiteOpenHelper {

    public static final String DBNAME = "Automon.db";

    public DBConexion(Context context) {
        super(context, "Automon.db", null, 2);
    }
    // Se crea la BBDD
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE usuarios " +
                "(intIdUser INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    intIdDepartamento int(10) not null,\n" +
                "    vchNombreUser varchar(50) unique not null,\n" +
                "    vchNombre varchar(20) not null,\n" +
                "    vchApellido1 varchar(20) not null,\n" +
                "    vchEmail varchar(50) unique not null,\n" +
                "    vchDni varchar(9) not null,\n" +
                "    vchPassword varchar(255) not null,\n" +
                "    vchRol varchar(20),\n" +
                "    vchEstado varchar(50),\n" +
                "    dateUltimaConexion datetime,\n" +
                "    foreign key (intIdDepartamento) references departamento(intIdDepartamento))");

        MyDB.execSQL("CREATE TABLE gastos " +
                "(intIdGasto INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "intIdProyecto INTEGER(10),\n" +
                "intIdDepartamento INTEGER(10),\n" +
                "intIdUser INTEGER(10),\n" +
                "dateFechaHora datetime,\n" +
                "doubleDistanciaTotalKm double(10,2),\n" +
                "vchMediotransporte varchar(25),\n" +
                "doublePeaje double(7,2),\n" +
                "doubleParking double(7,2),\n" +
                "doubleTotalFactura double,\n" +
                "constraint FK_intIdProyecto foreign key (intIdProyecto) references proyecto(intIdProyecto),\n" +
                "constraint FK2_intIdDepartamento foreign key (intIdDepartamento) references departamento(intIdDepartamento),\n" +
                "constraint FK_intIdUser foreign key (intIdUser) references usuarios(intIdUser))");

        MyDB.execSQL("CREATE TABLE dietas " +
                "(intIdDieta INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "intIdProyecto INTEGER(10),\n" +
                "intIdDepartamento INTEGER(10),\n" +
                "intIdUser INTEGER(10),\n" +
                "dateFechaHora datetime,\n" +
                "dateFechaInicio date,\n" +
                "dateFechafin date,\n" +
                "vchPais varchar(50),\n" +
                "vchCiudad varchar(50),\n" +
                "dateDiasTotales int,\n" +
                "doubleTotalFactura double,\n" +
                "constraint FK_intIdProyecto foreign key (intIdProyecto) references proyecto(intIdProyecto),\n" +
                "constraint FK2_intIdDepartamento foreign key (intIdDepartamento) references departamento(intIdDepartamento),\n" +
                "constraint FK_intIdUser foreign key (intIdUser) references usuarios(intIdUser))");

        MyDB.execSQL("CREATE TABLE departamentos " +
                "(intIdDepartamento INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "vchNombre varchar(50))");

        MyDB.execSQL("CREATE TABLE proyecto " +
                "(intIdProyecto INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "vchNombre varchar(50),\n" +
                "dateFechaInicio datetime,\n" +
                "dateFechaFin datetime)");

        MyDB.execSQL("CREATE TABLE fichar " +
                "(intIdFichar INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "intIdUser INTEGER(10),\n" +
                "intIdProyecto int(10),\n" +
                "estado varchar(10), \n" +
                "datetimeHoraEntrada time,\n" +
                "datetimeHoraSalida time ,\n" +
                "datefecha datetime,\n" +
                "horasDia time,\n" +
                "constraint FK2_intIdUser foreign key (intIdUser) references usuarios(intIdUser),\n" +
                "constraint FK2_intIdProyecto foreign key (intIdProyecto) references proyecto(intIdProyecto))");

        MyDB.execSQL("insert into departamentos (vchNombre) values \n" +
                "(\"Desarrollo\")," +
                "(\"Finanzas\")," +
                "(\"Transporte\")," +
                "(\"Informatica\")," +
                "(\"Abogados\")");

        MyDB.execSQL("insert into proyecto (vchNombre,dateFechaInicio,dateFechaFin) values \n" +
                "(\"Astro\",'2018-01-10','2020-04-10'),\n" +
                "(\"Beta\",'2015-11-00','2020-07-19'),\n" +
                "(\"Omega\",'2021-11-11','2025-01-01'),\n" +
                "(\"Blanco\",'2019-01-26','2020-01-26'),\n" +
                "(\"Aurora\",'2011-08-15','2021-12-20');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS usuarios");
        MyDB.execSQL("DROP TABLE IF EXISTS departamentos");
        MyDB.execSQL("DROP TABLE IF EXISTS gastos");
        MyDB.execSQL("DROP TABLE IF EXISTS proyecto");
        MyDB.execSQL("DROP TABLE IF EXISTS fichar");
    }

    // Insert username y password a la BBDD
    public boolean insertUser(
            String vchNombreUser, String vchNombre,
            String intIdDepartamento, String vchApellido1,
            String vchEmail, String vchDNI,
            String vchPassword) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vchNombreUser", vchNombreUser);
        contentValues.put("vchNombre", vchNombre);
        contentValues.put("vchApellido1", vchApellido1);
        contentValues.put("intIdDepartamento", intIdDepartamento);
        contentValues.put("vchEmail", vchEmail);
        contentValues.put("vchDNI", vchDNI);
        contentValues.put("vchPassword", vchPassword.hashCode());
        long result = MyDB.insert("usuarios",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    // Insertar gasto
    public boolean insertgasto(
            String intIdProyecto, String intIdDepartamento,
            String intIdUser, String doubleDistanciaTotalKm,
            String doublePeaje, String doubleParking,
            String vchMedioTransporte, String dateFechaHora,
            String doubleTotalFactura) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("intIdProyecto", intIdProyecto);
        contentValues.put("intIdDepartamento", intIdDepartamento);
        contentValues.put("intIdUser", intIdUser);
        contentValues.put("vchMedioTransporte", vchMedioTransporte);
        contentValues.put("doubleDistanciaTotalKm", doubleDistanciaTotalKm);
        contentValues.put("doublePeaje", doublePeaje);
        contentValues.put("doubleParking", doubleParking);
        contentValues.put("dateFechaHora", dateFechaHora);
        contentValues.put("doubleTotalFactura", doubleTotalFactura);
        long result = MyDB.insert("gastos",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    // Insertar gasto
    public boolean insertdieta(
            String intIdProyecto, String intIdDepartamento,
            String intIdUser, String dateFechaInicio,
            String dateFechaFin, String vchPais,
            String vchCiudad, String dateFechaHora,
            String dateDiasTotales, String doubleTotalFactura) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("intIdProyecto", intIdProyecto);
        contentValues.put("intIdDepartamento", intIdDepartamento);
        contentValues.put("intIdUser", intIdUser);
        contentValues.put("dateFechaInicio", dateFechaInicio);
        contentValues.put("dateFechaFin", dateFechaFin);
        contentValues.put("vchPais", vchPais);
        contentValues.put("vchCiudad", vchCiudad);
        contentValues.put("dateFechaHora", dateFechaHora);
        contentValues.put("dateDiasTotales", dateDiasTotales);
        contentValues.put("doubleTotalFactura", doubleTotalFactura);
        long result = MyDB.insert("dietas",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //Registrar una jornada

    public boolean insertjor(
            String intIdUser, String intIdProyecto,
            String datetimeHoraEntrada, String datetimeHoraSalida, String estado) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("intIdUser", intIdUser);
        contentValues.put("intIdProyecto", intIdProyecto);
        contentValues.put("datetimeHoraEntrada", datetimeHoraEntrada);
        contentValues.put("datetimeHoraSalida", datetimeHoraSalida);
        contentValues.put("estado", estado);
        long result = MyDB.insert("fichar", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //Actualizar hora salida

    public void salidaupdate (
            String intIdUser, String datetimeHoraSalida,
            String estado, String datefecha, String horasDia) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetimeHoraSalida", datetimeHoraSalida);
        contentValues.put("datefecha", datefecha);
        contentValues.put("estado", estado);
        contentValues.put("horasDia", horasDia);
        MyDB.update("fichar", contentValues, "intIdUser = ? and estado = 'activo'", new String[]{intIdUser});
    }

    //Consulta horarios

    public boolean info_hora(String intIdUser) {
        Cursor gast = getReadableDatabase().rawQuery("SELECT estado " +
                "FROM fichar WHERE intIdUser = ? AND estado = 'activo'", new String[]{intIdUser});
        if (gast.getCount()>0)
            return true;
        else
            return false;
    }

    //consulta8horas

    public ArrayList info8_horas(String intIdUser) {
        ArrayList<String> ochohoras = new ArrayList<>();
        Cursor horas = getReadableDatabase().rawQuery("SELECT * FROM fichar WHERE intIdUser = ?", new String[]{intIdUser});
        if (horas.moveToFirst()) {
            do {
                ochohoras.add(horas.getString(4));
            } while (horas.moveToNext());
        }
        return ochohoras;
    }

    //Consulta Jornadas

    public ArrayList info_jor(String intIdUser) {
        ArrayList<String> Jornadas = new ArrayList<>();
        Cursor jor = getReadableDatabase().rawQuery("SELECT * FROM fichar WHERE intIdUser = ?", new String[]{intIdUser});
        if (jor.moveToFirst()) {
            do {
                Jornadas.add(jor.getString(2)+" - "
                        +(jor.getString(3))+" - Total horas: "+(jor.getString(7)));
                Jornadas.add(jor.getString(4)+" - "
                        +(jor.getString(5)+" - "
                        +(jor.getString(6))));
            } while (jor.moveToNext());
        }
        return Jornadas;
    }

    //Consulta gastos

    public ArrayList info_gastos(String intIdUser, String intIdGasto) {
        ArrayList<String> Gastos = new ArrayList<>();
        Cursor gastos = getReadableDatabase().rawQuery("SELECT * FROM gastos WHERE intIdUser = ? AND intIdGasto = ?", new String[]{intIdUser, intIdGasto});
        if (gastos.moveToFirst()) {
            do {
                Gastos.add(gastos.getString(0));
                Gastos.add(gastos.getString(5));
                Gastos.add(gastos.getString(6));
                Gastos.add(gastos.getString(7));
                Gastos.add(gastos.getString(8));
                Gastos.add(gastos.getString(9));
            } while (gastos.moveToNext());
        }
        return Gastos;
    }

    //Consulta dietas

    public ArrayList info_dietas(String intIdUser, String intIdDieta) {
        ArrayList<String> Dietas = new ArrayList<>();
        Cursor dietas = getReadableDatabase().rawQuery("SELECT * FROM dietas WHERE intIdUser = ? AND intIdDieta = ?", new String[]{intIdUser, intIdDieta});
        if (dietas.moveToFirst()) {
            do {
                Dietas.add(dietas.getString(0));
                Dietas.add(dietas.getString(5));
                Dietas.add(dietas.getString(6));
                Dietas.add(dietas.getString(7));
                Dietas.add(dietas.getString(8));
                Dietas.add(dietas.getString(10));
            } while (dietas.moveToNext());
        }
        return Dietas;
    }

    //Consulta departamentos

    public List<Departamento> info_dep() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        Cursor dep = getReadableDatabase().rawQuery("SELECT * FROM departamentos", null);

        if (dep.moveToFirst()) {
            do {
                Departamento depa = new Departamento();
                depa.setIdDepartamento(dep.getString(0));
                depa.setNombre(dep.getString(1));
                departamentos.add(depa);
            } while (dep.moveToNext());
        }
        return departamentos;
    }

    //Consulta Proyectos

    public List<Proyectos> info_pro() {
        ArrayList<Proyectos> proyectos = new ArrayList<>();
        Cursor dep = getReadableDatabase().rawQuery("SELECT * FROM proyecto", null);

        if (dep.moveToFirst()) {
            do {
                Proyectos proye = new Proyectos();
                proye.setIdProyecto(dep.getString(0));
                proye.setNombrePro(dep.getString(1));
                proyectos.add(proye);
            } while (dep.moveToNext());
        }
        return proyectos;
    }

    //Consulta Usuario

    public ArrayList info_usr(String vchNombreUser) {
        ArrayList<String> Usuarios = new ArrayList<>();
        Cursor usrs = getReadableDatabase().rawQuery(
                "SELECT * FROM usuarios " +
                        "where vchNombreUser = ?", new String[]{String.valueOf(vchNombreUser)});
        if (usrs.moveToFirst()) {
            do {
                Usuarios.add(usrs.getString(2));
                Usuarios.add(usrs.getString(3)+" "+usrs.getString(4));
                Usuarios.add(usrs.getString(1));
                Usuarios.add(usrs.getString(5));
                Usuarios.add(usrs.getString(10));
                Usuarios.add(usrs.getString(0));
            } while (usrs.moveToNext());
        }
        return Usuarios;
    }

    //Actualizar Contraseña

    public void usrupdate(String vchPassword, String intIdUser){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        MyDB.execSQL("UPDATE usuarios SET vchNombreUser = ? WHERE intIdUser = ?", new String[] {vchPassword, intIdUser});
        MyDB.close();
    }

    //Actualizar ultima conexion

    public void dateupdate (String vchNombreUser){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        MyDB.execSQL("update usuarios set dateUltimaConexion= " +
                "date('now') where vchNombreUser= ?", new String[] {vchNombreUser});
        MyDB.close();
    }


    // Se asegura que el username no está duplicado
    public boolean checkusername(String vchNombreUser) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from usuarios where vchNombreUser = ?", new String[]{vchNombreUser} );
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    // Se asegura de que la contraseña sea la correcta
    public boolean checkusernamepassword(String vchNombreUser, String vchPassword) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from usuarios where vchNombreUser=? and vchPassword =?", new String[] {vchNombreUser, vchPassword});
        if (cursor.getCount()>0)
            return false;
        else
            return true;
    }

}