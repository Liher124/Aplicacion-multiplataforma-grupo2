package com.example.disenofinal2bien.Interfaz.Perfil;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.disenofinal2bien.DBConexion;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.R;

import java.util.ArrayList;


public class FragmentPerfil extends Fragment {

    Usuarios a;
    TextView nomtxt, departxt, correotxt, nomapetxt, datetxt;
    Button edit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_perfil, container, false);

        a = new ViewModelProvider(requireActivity()).get(Usuarios.class);

        departxt = view.findViewById(R.id.departxt);
        correotxt = view.findViewById(R.id.correotxt);
        nomapetxt = view.findViewById(R.id.nomapetxt);
        datetxt = view.findViewById(R.id.datetxt);

            departxt.setText(a.getDepartamento().getValue());
            correotxt.setText(a.getCorreo().getValue());
            nomapetxt.setText(a.getNombre().getValue());
            datetxt.setText(a.getDate().getValue());


        nomtxt = view.findViewById(R.id.nomtxt);
        nomtxt.setText(a.getNombreuser().getValue());

        //edit = view.findViewById(R.id.editusr);

        /*edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getActivity(), EditarUsuario.class);
                edit.putExtra("usuario", String.valueOf(nomtxt));
                startActivity(edit);
            }
        });*/

        return view;
    }
}