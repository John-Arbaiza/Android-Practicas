package com.example.primerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class principal extends AppCompatActivity {

    //Deffinimos los atributos
    public TextView lblname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        //Obtenemos el Intent que se utilizó para iniciar la actividad
        Intent intent = getIntent();
        String user = intent.getStringExtra("nombreUsuario");

        lblname = findViewById(R.id.textView5);

        // Verificar que user no sea nulo antes de establecerlo en lblname
        if (user != null) {
            lblname.setText(user);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}