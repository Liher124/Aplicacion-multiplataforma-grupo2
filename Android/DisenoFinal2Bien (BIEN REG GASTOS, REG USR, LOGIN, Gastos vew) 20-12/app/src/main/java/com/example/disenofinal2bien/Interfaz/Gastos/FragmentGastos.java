package com.example.disenofinal2bien.Interfaz.Gastos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.R;

import java.util.ArrayList;

public class FragmentGastos extends Fragment {

    Spinner spelegir;
    EditText edid;
    TextView idgd, usrgd, kmsgd, mtransgd, peajegd, parkgd;
    TextView fecha1, fecha2, pais, ciudad, totalfactura, texttofac;
    Button btfiltro;
    Usuarios a;
    ArrayList<String> Gastos, Dietas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);

        spelegir = view.findViewById(R.id.spgastodieta);
        String[] opciones = {
                "Gastos","Dietas"};
        this.spelegir.setAdapter(new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,
                        opciones));

        usrgd = view.findViewById(R.id.txtusr);
        idgd = view.findViewById(R.id.txtid);
        kmsgd = view.findViewById(R.id.txtkmgas);
        mtransgd = view.findViewById(R.id.txttrans);
        peajegd = view.findViewById(R.id.txtpeaje);
        parkgd = view.findViewById(R.id.txtparking);
        totalfactura = view.findViewById(R.id.txtfacturagd);

        fecha1 = view.findViewById(R.id.txttrans_fini);
        fecha2 = view.findViewById(R.id.txtkms_ffin);
        pais = view.findViewById(R.id.txtpeaje_pais);
        ciudad = view.findViewById(R.id.txtpark_ciudad);

        btfiltro = view.findViewById(R.id.btfiltro);
        edid = view.findViewById(R.id.edfiltroid);

        btfiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spelegir.getSelectedItem().equals("Gastos")){

                    String edfiltro = edid.getText().toString();

                    a = new ViewModelProvider(requireActivity()).get(Usuarios.class);
                    String id = String.valueOf(a.getIdusuario().getValue());
                    DBConexion dBConexion = new DBConexion(getActivity());
                    Gastos = dBConexion.info_gastos(id, edfiltro);
                    if(Gastos.isEmpty()){
                        Toast.makeText(getActivity(), "No hay gastos asociados a ese ID", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        fecha1.setText("Transporte:");
                        fecha2.setText("Total KMs:");
                        pais.setText("Peaje:");
                        ciudad.setText("Parking:");

                        idgd.setText(Gastos.get(0));
                        usrgd.setText(a.getNombreuser().getValue());
                        kmsgd.setText(Gastos.get(1)+" KMs");
                        mtransgd.setText(Gastos.get(2));
                        peajegd.setText(Gastos.get(3)+"€");
                        parkgd.setText(Gastos.get(4)+"€");
                        totalfactura.setText(Gastos.get(5)+"€");
                    }
                }
                else{
                    String edfiltro = edid.getText().toString();
                    a = new ViewModelProvider(requireActivity()).get(Usuarios.class);
                    String id = String.valueOf(a.getIdusuario().getValue());
                    DBConexion dBConexion = new DBConexion(getActivity());
                    Dietas = dBConexion.info_dietas(id, edfiltro);

                    if(Dietas.isEmpty()){
                        Toast.makeText(getActivity(), "No hay dietas asociadas a ese ID", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        fecha1.setText("Fecha Inicio:");
                        fecha2.setText("Fecha Fin:");
                        pais.setText("Pais:");
                        ciudad.setText("Ciudad:");

                        idgd.setText(Dietas.get(0));
                        usrgd.setText(a.getNombreuser().getValue());
                        kmsgd.setText(Dietas.get(1));
                        mtransgd.setText(Dietas.get(2));
                        peajegd.setText(Dietas.get(3));
                        parkgd.setText(Dietas.get(4));
                        totalfactura.setText(Dietas.get(5)+"€");
                    }
                }
            }
        });

        return view;
    }
}