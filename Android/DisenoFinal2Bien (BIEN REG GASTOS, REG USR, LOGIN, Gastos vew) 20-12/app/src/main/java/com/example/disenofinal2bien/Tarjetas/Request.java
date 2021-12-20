package com.example.disenofinal2bien.Tarjetas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.disenofinal2bien.Interfaz.Entidades.Usuarios;
import com.example.disenofinal2bien.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.GeneralSecurityException;

public class Request extends AppCompatActivity {

    TextView textView;
    TextView textview2;
    RequestQueue requestQueue;
    private static final String URL1= "http://10.122.27.162:4000/infocards1/user1";
    private static final String password = "KeyMustBe16ByteOR24ByteOR32ByT1!";
    private static final String message = "YqJNRCivg0ULQsaTp5NEAuyom6jOyMBQCIXNWEoWam+8g3" +
            "jOlKWFNbKsZiANNho0rNuFIt4PNZ+s1d03jjplYbLMn6li14Yt++YK7ePcF1/8GOA9bTQ+wh0HLEgoC" +
            "fj86S+GEUriJ5hvOyp/rB9trvZLiB8vvhHUsKwa5pO4pCsr/HHTX5BZ3M/KnWkIRDTrgTq/OVzyWFd0x1" +
            "vpZLfk4HxCiF1Q81AgqR0TX5eSr3YLLS+rVMn/V5PMtzmTjlEc0YSXGJb8vvkZ9i8OZMV4rg==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        Button bt_request = findViewById(R.id.mostrar);
        Button bt_volver = findViewById(R.id.bt_volver);
        Button main_change = findViewById(R.id.main_change);
        TextView titular = findViewById(R.id.txttitular);
        String usr = getIntent().getStringExtra("usuario");
        titular.setText(usr);

        main_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IFChange();


            }
        });

        bt_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        requestQueue= Volley.newRequestQueue(this);
        // ui

        textView = findViewById(R.id.numtarjeta);
        textView.setVisibility(View.GONE);

        textview2 = findViewById(R.id.eurointer);
        textview2.setVisibility(View.GONE);

        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringRequest();
                //credit_card.setScaleType(ImageView.ScaleType.FIT_START);
                textview2.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void stringRequest(){
        StringRequest request= new StringRequest(com.android.volley.Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    String dc_crypt = Desencriptado.decrypt(password, message);
                    JSONObject jsonObject = new JSONObject(dc_crypt);
                    String current_card = jsonObject.getString("current_card");
                    String card_europe = jsonObject.getString("card_europe");
                    String card_international = jsonObject.getString("card_international");
                    textview2.setText(current_card);
                    textView.setText(card_international);

                } catch (GeneralSecurityException | JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage());
                    }
                }
        );
        requestQueue.add(request);
    }

    public void IFChange() {
        StringRequest request= new StringRequest(com.android.volley.Request.Method.GET, URL1, new Response.Listener<String>() {
            private Object JsonObj;

            @Override
            public void onResponse(String response) {
                try {

                    String dc_crypt = Desencriptado.decrypt(password, message);
                    JSONObject jsonObject = new JSONObject(dc_crypt);
                    String current_card = jsonObject.getString("current_card");
                    String card_europe = jsonObject.getString("card_europe");
                    String card_international = jsonObject.getString("card_international");
                    String tarjeta1 = textview2.getText().toString();
                    //String tarjeta2 = textView.getText().toString();

                    if (tarjeta1 == "INTERNATIONAL") {
                        textview2.setText("EUROPE");
                        textView.setText(card_europe);
                    } else {
                        textview2.setText("INTERNATIONAL");
                        textView.setText(card_international);
                    }

                } catch (GeneralSecurityException | JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText(error.getMessage());
                    }
                }
        );
        requestQueue.add(request);
    }

}
