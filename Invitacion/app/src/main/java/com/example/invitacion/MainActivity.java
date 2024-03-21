package com.example.invitacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Definimos una variable con el tipo de control
    Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parte encargada del funcionamiento de los buttons

        //capturamos el id
        btnSiguiente = findViewById(R.id.btnInicio);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            //Evento click
            @Override
            public void onClick(View v) {
                //Especificamos a la actividad que queremos ir
                Intent intent = new Intent(MainActivity.this, invitacion.class);
                startActivity(intent);

            }
        });
    }




}