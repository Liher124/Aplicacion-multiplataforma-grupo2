package com.example.disenofinal2bien.Interfaz.Perfil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.R;

public class EditarUsuario extends AppCompatActivity implements View.OnClickListener {

    Button pass, cor, bt_volver3;
    EditText usr1, usr2;
    Usuarios a;
    ImageView imgview;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_usuario);

        pass = findViewById(R.id.passch);
        cor = findViewById(R.id.corch);
        usr1 = findViewById(R.id.pass1);
        usr1 = findViewById(R.id.pass2);
        bt_volver3 = findViewById(R.id.bt_volver3);

        pass.setOnClickListener(this);
        bt_volver3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==pass){

            final DBConexion DB = new DBConexion(getApplicationContext());

            String intent_user = getIntent().getStringExtra("usuario");
            a = new ViewModelProvider(this).get(Usuarios.class);
            String idusr = String.valueOf(a.getIdusuario().getValue());

            String nomusr = usr1.getText().toString();

                DB.usrupdate(nomusr, idusr);
                Toast.makeText(getApplicationContext(), "Se ha modificado su nombre de usuario", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "No se ha podido modificar su nombre de usuario", Toast.LENGTH_SHORT).show();
        }
        if(v==bt_volver3){
            finish();
        }
    }
}
