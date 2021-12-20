package com.example.disenofinal2bien;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainLogin extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;
    TextView txt1;
    DBConexion DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        txt1 = findViewById(R.id.textView4);


        DB = new DBConexion(this);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(getApplicationContext(), MainRegistro.class);
                startActivity(reg);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ed1.getText().toString();
                int pass = ed2.getText().toString().hashCode();

                // Si no insertas ningun dato, te aparece un mensaje de error
                if(user.equals("") || ed2.equals(""))
                Toast.makeText(MainLogin.this, "Inserta todos los datos", Toast.LENGTH_SHORT).show();
                 else{
                    // Cuando se confirma que usuario y contraseña con correctos mediante el metodo checkuserpass
                    // te manda a la HomeActivity
                    DB.dateupdate(user);
                    Boolean checkuserpass = DB.checkusernamepassword(user, String.valueOf(pass));
                    if (checkuserpass==false){
                        Toast.makeText(MainLogin.this, "Bienvenido " +user+"", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("usuario", user);
                        startActivity(intent);
                    }else{
                        // En caso de error, aparece el mensaje: "Las credenciales son invalidass"
                        Toast.makeText(MainLogin.this, "Introduzca unas credenciales validas", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
