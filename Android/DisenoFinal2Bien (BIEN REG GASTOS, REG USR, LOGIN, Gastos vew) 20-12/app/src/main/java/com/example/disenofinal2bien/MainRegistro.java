package com.example.disenofinal2bien;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.disenofinal2bien.Interfaz.Entidades.Departamento;

import java.util.ArrayList;
import java.util.List;


public class MainRegistro extends AppCompatActivity {

    TextView txt2, txt3;
    EditText usr, password, repassword, nompro, ape1, email, dni;
    Button singup;
    DBConexion DB;
    List<Departamento> departamentos;
    ArrayAdapter adapter;
    String idDepartamento;
    Spinner depart;
    CheckBox chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        usr = findViewById(R.id.username);
        nompro = findViewById(R.id.nompropio);
        ape1 = findViewById(R.id.ape1);
        depart = findViewById(R.id.depart);
        email = findViewById(R.id.correo);
        dni = findViewById(R.id.dni);
        password = findViewById(R.id.pass);
        repassword = findViewById(R.id.repass);
        singup = findViewById(R.id.button4);
        chk = findViewById(R.id.chk);
        DB = new DBConexion(this);

        txt2 = findViewById(R.id.textView7);
        txt3 = findViewById(R.id.textView8);

        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent texto = new Intent(getApplicationContext(), Ajustes.class);
                startActivity(texto);
            }
        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(getApplicationContext(), MainLogin.class);
                startActivity(log);
            }
        });

        DBConexion dBConexion = new DBConexion(this);
        this.DB = dBConexion;
        this.departamentos = dBConexion.info_dep();
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, extraerNombredep(this.departamentos));
        this.adapter = arrayAdapter;
        this.depart.setAdapter((SpinnerAdapter) arrayAdapter);

            singup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nomDepart = depart.getSelectedItem().toString();

                    for (Departamento item : departamentos) {
                        if (item.getNombre().equals(nomDepart)) {
                            idDepartamento = item.getIdDepartamento();
                            break;
                        }
                    }

                    String user = usr.getText().toString();
                    String pass = password.getText().toString();
                    String nom = nompro.getText().toString();
                    String priape = ape1.getText().toString();
                    String correo = email.getText().toString();
                    String dni1 = dni.getText().toString();
                    String repass = repassword.getText().toString();

                    if (chk.isChecked()) {

                        if (user.equals("") || pass.equals("") || repass.equals("") || nom.equals("") || priape.equals("") || dni1.equals("") || correo.equals(""))
                            Toast.makeText(MainRegistro.this, "Por favor inserte todos los datos", Toast.LENGTH_SHORT).show();
                        else {
                            if (pass.equals(repass)) {
                                Boolean checkuser = DB.checkusername(user);
                                if (checkuser == false) {
                                    Boolean insert = DB.insertUser(user, nom, idDepartamento, priape, correo, dni1, pass);
                                    if (insert == true) {
                                        Toast.makeText(MainRegistro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("usuario", user);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainRegistro.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainRegistro.this, "Ya existe este nombre de usuario", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainRegistro.this, "Contraseñas diferentes", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else {
                        Toast.makeText(MainRegistro.this, "Acepte los terminos de licencia", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    private ArrayList<String> extraerNombredep(List<Departamento> departamentos) {
        ArrayList<String> nombres = new ArrayList<String>();
        for (Departamento item:departamentos) {
            nombres.add(item.getNombre());
        }

        return nombres;
    }

}