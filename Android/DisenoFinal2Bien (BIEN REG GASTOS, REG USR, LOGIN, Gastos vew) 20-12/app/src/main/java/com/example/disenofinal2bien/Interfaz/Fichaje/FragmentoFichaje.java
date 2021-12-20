package com.example.disenofinal2bien.Interfaz.Fichaje;

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
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Fichaje.Fichaje;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.Interfaz.Gastos.AñadirGasto;
import com.example.disenofinal2bien.R;

import java.util.ArrayList;


public class FragmentoFichaje extends Fragment {

    Usuarios a;
    Button reg;
    DBConexion DB;
    ArrayList<String> Jornada;
    ArrayAdapter adaptador;
    ListView lv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_fichaje, container, false);

        reg = view.findViewById(R.id.añadirjor);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent añadirjor = new Intent(getActivity(), AñadirGasto.class);
                startActivity(añadirjor);
            }
        });
        a = new ViewModelProvider(requireActivity()).get(Usuarios.class);
        String id = String.valueOf(a.getIdusuario().getValue());
        DBConexion dBConexion = new DBConexion(getActivity());
        this.DB = dBConexion;
        this.lv = view.findViewById(R.id.jornadaslv);
        this.Jornada = dBConexion.info_jor(id);
        ArrayAdapter arrayAdapter = new ArrayAdapter
                (getActivity(), android.R.layout.simple_list_item_1, Jornada);
        this.adaptador = arrayAdapter;
        this.lv.setAdapter((ListAdapter) arrayAdapter);
        return view;
    }
}