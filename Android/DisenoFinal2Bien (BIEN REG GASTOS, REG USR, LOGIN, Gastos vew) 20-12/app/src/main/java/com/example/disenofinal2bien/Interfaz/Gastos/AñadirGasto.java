package com.example.disenofinal2bien.Interfaz.Gastos;

import static java.time.temporal.ChronoUnit.DAYS;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Departamento;
import com.example.disenofinal2bien.Interfaz.Entidades.Fechas;
import com.example.disenofinal2bien.Interfaz.Entidades.Proyectos;
import com.example.disenofinal2bien.MainActivity;
import com.example.disenofinal2bien.R;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AñadirGasto extends AppCompatActivity implements View.OnClickListener {

    Button anadir, btvolver2, dieta,
            cambiogasto, btnanadirdieta,
            btnfechaini, btnfechafin;
    EditText txtkm;
    EditText txtpeaje,
            txtpark, txtpais,
            txtciudad;
    TextView txttitulo, txtfini,
            txtffin;
    DBConexion DB;
    List<Departamento> departamentos;
    List<Proyectos> proyectos;
    ArrayAdapter adapter_pro, adapter_depart;
    Spinner proyec, depart,
            trans, sptarjeta;

    String idDepartamento, idProyecto;
    private int dia, mes,
            ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_gasto);

        btnanadirdieta = findViewById(R.id.btnanadirdieta);
        txtfini = findViewById(R.id.txtfechaini);
        txtffin = findViewById(R.id.txtfechafin);
        btnfechaini = findViewById(R.id.btnfechaini);
        btnfechafin = findViewById(R.id.btnfechafin);
        txtkm = findViewById(R.id.txtkm);
        txtpark = findViewById(R.id.txtpark);
        txtpeaje = findViewById(R.id.txtpeje);
        anadir = findViewById(R.id.btnanadir);
        proyec = findViewById(R.id.sppro);
        trans = findViewById(R.id.sptransporte);
        depart = findViewById(R.id.spdepart);
        dieta = findViewById(R.id.btndieta);
        txttitulo = findViewById(R.id.txtitulo);
        cambiogasto = findViewById(R.id.btncambiogasto);
        txtpais = findViewById(R.id.txtpais);
        txtciudad = findViewById(R.id.txtciudad);
        sptarjeta = findViewById(R.id.sptarjeta);
        btvolver2 = findViewById(R.id.bt_volver2);

        txtkm.setVisibility(View.VISIBLE);
        txtpark.setVisibility(View.VISIBLE);
        txtpeaje.setVisibility(View.VISIBLE);
        anadir.setVisibility(View.VISIBLE);
        proyec.setVisibility(View.VISIBLE);
        trans.setVisibility(View.VISIBLE);
        depart.setVisibility(View.VISIBLE);
        btnanadirdieta.setVisibility(View.GONE);
        cambiogasto.setVisibility(View.GONE);
        txtfini.setVisibility(View.GONE);
        txtffin.setVisibility(View.GONE);
        btnfechaini.setVisibility(View.GONE);
        btnfechafin.setVisibility(View.GONE);
        txtpais.setVisibility(View.GONE);
        txtciudad.setVisibility(View.GONE);
        sptarjeta.setVisibility(View.GONE);

        btnfechaini.setOnClickListener(this);
        btnfechafin.setOnClickListener(this);
        anadir.setOnClickListener(this);
        btnanadirdieta.setOnClickListener(this);

        btvolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salir = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(salir);
            }
        });

        dieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtkm.setVisibility(View.GONE);
                txtpark.setVisibility(View.GONE);
                txtpeaje.setVisibility(View.GONE);
                anadir.setVisibility(View.GONE);
                trans.setVisibility(View.GONE);
                dieta.setVisibility(View.GONE);
                txttitulo.setText("Añadir una dieta");
                sptarjeta.setVisibility(View.VISIBLE);
                txtfini.setVisibility(View.VISIBLE);
                txtffin.setVisibility(View.VISIBLE);
                txtpais.setVisibility(View.VISIBLE);
                txtciudad.setVisibility(View.VISIBLE);
                btnfechaini.setVisibility(View.VISIBLE);
                btnfechafin.setVisibility(View.VISIBLE);
                cambiogasto.setVisibility(View.VISIBLE);
                btnanadirdieta.setVisibility(View.VISIBLE);
            }
        });
        cambiogasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtkm.setVisibility(View.VISIBLE);
                txtpark.setVisibility(View.VISIBLE);
                txtpeaje.setVisibility(View.VISIBLE);
                anadir.setVisibility(View.VISIBLE);
                trans.setVisibility(View.VISIBLE);
                txttitulo.setText("Añadir un gasto");
                dieta.setVisibility(View.VISIBLE);
                txtfini.setVisibility(View.GONE);
                sptarjeta.setVisibility(View.GONE);
                txtffin.setVisibility(View.GONE);
                txtpais.setVisibility(View.GONE);
                txtciudad.setVisibility(View.GONE);
                btnfechaini.setVisibility(View.GONE);
                btnfechafin.setVisibility(View.GONE);
                cambiogasto.setVisibility(View.GONE);
                btnanadirdieta.setVisibility(View.GONE);
            }
        });

        DBConexion dBConexion = new DBConexion(this);
        this.DB = dBConexion;

        this.proyectos = DB.info_pro();

        ArrayAdapter arrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, extraerNombrepro(this.proyectos));
        this.adapter_pro = arrayAdapter;
        this.proyec.setAdapter((SpinnerAdapter) arrayAdapter);

        this.departamentos = DB.info_dep();

        ArrayAdapter aradptr = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, extraerNombredep(this.departamentos));
        this.adapter_depart = aradptr;
        this.depart.setAdapter((SpinnerAdapter) aradptr);

        String[] tarjeta = {
                "Europea", "Internacional"};
        this.sptarjeta.setAdapter(new ArrayAdapter<String>
                (AñadirGasto.this,
                        android.R.layout.simple_spinner_item,
                        tarjeta));

        String[] transporte = {
                "Medio de transporte",
                "Coche", "Autobus",
                "Taxi", "Tren",
                "Metro"};
        this.trans.setAdapter(new ArrayAdapter<String>
                (AñadirGasto.this,
                        android.R.layout.simple_spinner_item,
                        transporte));
        this.DB = dBConexion;
    }

    private ArrayList<String> extraerNombrepro(List<Proyectos> proyectos) {
        ArrayList<String> nombrespro = new ArrayList<String>();
        for (Proyectos item : proyectos) {
            nombrespro.add(item.getNombrePro());
        }

        return nombrespro;
    }

    private ArrayList<String> extraerNombredep(List<Departamento> departamentos) {
        ArrayList<String> nombres = new ArrayList<String>();
        for (Departamento item : departamentos) {
            nombres.add(item.getNombre());
        }

        return nombres;
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {

        if (v == btnfechaini) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                            txtfini.setText(ano + "/" + (mes + 1) + "/" + dia);
                        }
                    }
                            , ano, mes, dia);
            datePickerDialog.show();
        }

        if (v == btnfechafin) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                            txtffin.setText(ano + "/" + (mes + 1) + "/" + dia);

                        }
                    }
                            , ano, mes, dia);
            datePickerDialog.show();
        }

        if (v == anadir) {

            String nomDepart = depart.getSelectedItem().toString();

            for (Departamento item : departamentos) {
                if (item.getNombre().equals(nomDepart)) {
                    idDepartamento = item.getIdDepartamento();
                    break;
                }
            }
            String nompro = proyec.getSelectedItem().toString();

            for (Proyectos item : proyectos) {
                if (item.getNombrePro().equals(nompro)) {
                    idProyecto = item.getIdProyecto();
                    break;
                }
            }
            String idin = getIntent().getStringExtra("id");
            String trans = this.trans.getSelectedItem().toString();
            String km = txtkm.getText().toString();
            String peaje = txtpeaje.getText().toString();
            String park = txtpark.getText().toString();
            Date hora = Calendar.getInstance().getTime();
            String fhora = String.valueOf(hora);
            float kmint = Integer.parseInt(km);
            float peajeint = Integer.parseInt(km);
            float parkint = Integer.parseInt(km);
            float totalgasto = (float) (kmint*0.3 + peajeint + parkint);


            if (trans.equals("") || km.equals("") || peaje.equals("") || park.equals("")) {
                Toast.makeText(AñadirGasto.this, "Parece que falta algun dato", Toast.LENGTH_SHORT).show();
            } else {

                Boolean insergasto = DB.insertgasto(idProyecto, idDepartamento, idin, km, peaje, park, trans, fhora, String.valueOf(totalgasto));
                if (insergasto == true) {
                    Toast.makeText(AñadirGasto.this, "Los datos se han añadido correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AñadirGasto.this, "No se han podido añadir los datos", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (v == btnanadirdieta) {

            String nomDepart = depart.getSelectedItem().toString();

            for (Departamento item : departamentos) {
                if (item.getNombre().equals(nomDepart)) {
                    idDepartamento = item.getIdDepartamento();
                    break;
                }
            }
            String nompro = proyec.getSelectedItem().toString();

            for (Proyectos item : proyectos) {
                if (item.getNombrePro().equals(nompro)) {
                    idProyecto = item.getIdProyecto();
                    break;
                }
            }

            String idin = getIntent().getStringExtra("id");
            String pais = txtpais.getText().toString();
            String ciudad = txtciudad.getText().toString();
            Date hora = Calendar.getInstance().getTime();
            String fhora = String.valueOf(hora);
            DateTimeFormatter formato = DateTimeFormat.forPattern("yyyy/MM/dd");
            String fini = txtfini.getText().toString();
            String ffin = txtffin.getText().toString();
            DateTime f1 = DateTime.parse(fini, formato);
            DateTime f2 = DateTime.parse(ffin, formato);
            long diferencia = f2.toDate().getTime() - f1.toDate().getTime();
            float diff = (diferencia / (1000 * 60 * 60 * 24));
            String total = String.valueOf(diff);
            String tarjeta = this.sptarjeta.getSelectedItem().toString();
            if (tarjeta == "Europea") {
                float diftotal = diff * 60;

                if (fini.equals("") || ffin.equals("") || pais.equals("") || ciudad.equals("")) {
                    Toast.makeText(AñadirGasto.this, "Parece que falta algun dato", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean insertdieta = DB.insertdieta(idProyecto, idDepartamento, idin, fini, ffin, pais, ciudad, fhora, total, String.valueOf(diftotal));
                    if (insertdieta == true) {
                        Toast.makeText(AñadirGasto.this, "Los datos se han añadido correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AñadirGasto.this, "No se han podido añadir los datos", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                float diftotal = diff * 100;
                Boolean insertdieta = DB.insertdieta(idProyecto, idDepartamento, idin, fini, ffin, pais, ciudad, fhora, total, String.valueOf(diftotal));
                if (insertdieta == true) {
                    Toast.makeText(AñadirGasto.this, "Los datos se han añadido correctamente", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}
