package com.example.disenofinal2bien.Interfaz.Inicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.Interfaz.Fichaje.Fichaje;
import com.example.disenofinal2bien.Interfaz.Gastos.AñadirGasto;
import com.example.disenofinal2bien.R;
import com.example.disenofinal2bien.Tarjetas.Request;

import java.util.ArrayList;


public class FragmentoInicio extends Fragment {

    Usuarios a;
    TextView nomwelcome;
    Button btnfichar, btngasto, bt_tarjeta;
    DBConexion DB;
    ArrayList<String> Usuario;
    ArrayAdapter adaptador;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        nomwelcome = view.findViewById(R.id.nomwelcome);
        btnfichar = view.findViewById(R.id.btnfichar);
        btngasto = view.findViewById(R.id.btngasto);
        bt_tarjeta = view.findViewById(R.id.bttarjeta);

        a = new ViewModelProvider(requireActivity()).get(Usuarios.class);
        String nom = String.valueOf(a.getNombreuser().getValue());
        nomwelcome.setText(" "+nom);

        DBConexion dBConexion = new DBConexion(getActivity());
        this.DB = dBConexion;
        this.Usuario = dBConexion.info_usr(nom);
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (getActivity(), android.R.layout.simple_list_item_1, Usuario);
        this.adaptador = arrayAdapter;

        for(int i=0; i<arrayAdapter.getCount(); i++) {
            String depart = String.valueOf(arrayAdapter.getItem(2));
            String nomape = String.valueOf(arrayAdapter.getItem(1));
            String correo = String.valueOf(arrayAdapter.getItem(3));
            String date = String.valueOf(arrayAdapter.getItem(4));
            String id = String.valueOf(arrayAdapter.getItem(5));


            a.setDepartamento(depart);
            a.setCorreo(correo);
            a.setNombre(nomape);
            a.setDate(date);
            a.setIdusuario(Integer.valueOf(id));

        }


String idin = String.valueOf(a.getIdusuario().getValue());

        btnfichar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fichar = new Intent(getActivity(), Fichaje.class);
                fichar.putExtra("usuario", nom);
                fichar.putExtra("id", idin);
                startActivity(fichar);
            }
        });

        btngasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fichar = new Intent(getActivity(), AñadirGasto.class);
                fichar.putExtra("usuario", a.getNombreuser().getValue());
                fichar.putExtra("id", idin);
                startActivity(fichar);
            }
        });
        bt_tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Request.class);
                intent.putExtra("usuario", nom);
                startActivity(intent);
            }
        });

        return view;
    }
}