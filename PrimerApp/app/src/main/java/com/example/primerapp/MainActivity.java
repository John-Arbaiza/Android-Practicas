package com.example.primerapp;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Definiendo los atributos
    public Button btn1;
    public EditText txtName, txtPassword;
    public TextView mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Capturando los id
        btn1 = findViewById(R.id.button);
        txtName = findViewById(R.id.editTextText2);
        txtPassword = findViewById(R.id.editTextTextPassword);
        mensaje = findViewById(R.id.textView4);

        //Evento del boton
         btn1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //Definimos las variables a emplear
                 String contra = "android01"; //Establecemos una password por defecto
                 String pass = txtPassword.getText().toString();
                 String user = txtName.getText().toString();
                 String message = "Password Incorrecta";

                 //verificamos el ingreso
                 if(pass.equals(contra)){
                     //Indicamos a la actividad a la que queremos pasar
                     Intent intent = new Intent(MainActivity.this, principal.class);

                     //Indicamos que queremos pasar el nombre de usuario a la otra actividad
                     intent.putExtra("nombreUsuario", user); // "nombreUsuario" es la clave para el extra
                     startActivity(intent);
                 }
                 else {
                     mensaje.setText(String.valueOf(message));
                 }
             }
         });

    }
}