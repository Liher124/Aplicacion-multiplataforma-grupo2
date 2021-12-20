package com.example.disenofinal2bien.Interfaz.Fichaje;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.sax.StartElementListener;
import android.view.View;
import android.view.ViewStructure;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Proyectos;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.MainActivity;
import com.example.disenofinal2bien.MainRegistro;
import com.example.disenofinal2bien.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Fichaje extends AppCompatActivity {

    Usuarios a;
    TextView hora;
    Button salir, regjor, regjor2;
    DBConexion DB;
    ArrayList<String> horaen;
    List<Proyectos> proyectos;
    ArrayAdapter adapter;
    Spinner proyec;
    String idProyecto, hentrada;
    Time he, hs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fichaje);

        DBConexion dBConexion = new DBConexion(this);
        String idin = getIntent().getStringExtra("id");
        boolean pepe =  dBConexion.info_hora(idin);
        proyec = findViewById(R.id.proyectosp);
        this.proyectos = dBConexion.info_pro();
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, extraerNombrepro(this.proyectos));
        this.adapter = arrayAdapter;
        this.proyec.setAdapter((SpinnerAdapter) arrayAdapter);

        a = new ViewModelProvider(this).get(Usuarios.class);

        String horaview = DateFormat.getTimeInstance().format(new Date());
        DB = new DBConexion(this);

        regjor = findViewById(R.id.regjor);
        regjor2 = findViewById(R.id.regjor2);
        salir = findViewById(R.id.salir);
        hora = findViewById(R.id.hora);
        hora.setText(horaview);

        if(pepe == true){

                regjor2.setVisibility(View.VISIBLE);
                regjor.setVisibility(View.GONE);

                regjor2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String idin = getIntent().getStringExtra("id");
                        String fechaview = DateFormat.getDateInstance().format(new Date());
                        String horasal = hora.getText().toString();
                        String datefecha = fechaview;
                        String inactivo = "inactivo";

                        horaen = DB.info8_horas(idin);
                        String hentrada = horaen.get(0);

                        DateFormat df = new SimpleDateFormat("HH");
                        try {
                            he = new Time(df.parse(hentrada).getTime());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            hs = new Time(df.parse(horasal).getTime());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int timestart = Integer.parseInt(df.format(he));
                        int timefinish = Integer.parseInt(df.format(hs));
                        int diff = timefinish - timestart;

                        if (diff >=8){
                            Toast.makeText(Fichaje.this, "Ha cumplido las 8 horas correspondientes", Toast.LENGTH_SHORT).show();
                            Toast.makeText(Fichaje.this, "La jornada se ha cerrado automaticamente", Toast.LENGTH_SHORT).show();
                            DB.salidaupdate(idin, horasal, inactivo, datefecha, String.valueOf(diff));
                            boolean pepe1 = dBConexion.info_hora(idin);
                        }
                        else {

                            DB.salidaupdate(idin, horasal, inactivo, datefecha, String.valueOf(diff));
                            boolean pepe1 = dBConexion.info_hora(idin);
                            if (pepe1 == false) {
                                Toast.makeText(Fichaje.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Fichaje.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        else {
            regjor2.setVisibility(View.GONE);
            regjor.setVisibility(View.VISIBLE);

                regjor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String nompro = proyec.getSelectedItem().toString();

                        for (Proyectos item : proyectos) {
                            if (item.getNombrePro().equals(nompro)) {
                                idProyecto = item.getIdProyecto();
                                break;
                            }
                        }

                        String idin = getIntent().getStringExtra("id");
                        String horaen = hora.getText().toString();
                        String horaen2 = hora.getText().toString();
                        String activo = "activo";


                        Boolean insert = DB.insertjor(idin, idProyecto, horaen,horaen2, activo);
                        if (insert == true) {
                            Toast.makeText(Fichaje.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Fichaje.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                        }
                }

            });
            salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent salir = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(salir);
                }
            });
        }
        }

    private ArrayList<String> extraerNombrepro(List<Proyectos> proyectos) {
        ArrayList<String> nombrespro = new ArrayList<String>();
        for (Proyectos item:proyectos) {
            nombrespro.add(item.getNombrePro());
        }

        return nombrespro;
    }
}

