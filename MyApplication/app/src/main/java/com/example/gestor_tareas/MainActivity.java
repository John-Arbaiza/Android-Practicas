package com.example.gestor_tareas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //------------------------------------------------------------------------------
    //Definiendo los atributos y mas
    //------------------------------------------------------------------------------
    public EditText txtTareas;
    public RadioButton rbAlta, rbMedia, rbBaja;
    public Spinner spinner1;
    public Button btnRegistrar;
    //-----------------------------------------------------
    //Definimos un arrayList para los valores del spinner
    public ArrayList<categorias> listCategorias;
    //------------------------------------------------------
    //Para la lista
    public ArrayList<Tareas> listTareas;
    //definimos un listView
    public ListView listaView;
    //public TextView txt1;
    public String tarea;
    public String prioridad;
    //------------------------------------------------------------------------------

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

        //------------------------------------------------------------------------------
        //Capturando los id de los elementos
        //------------------------------------------------------------------------------
        txtTareas = findViewById(R.id.editTextText);
        rbAlta = findViewById(R.id.radioButton);
        rbMedia = findViewById(R.id.radioButton2);
        rbBaja = findViewById(R.id.radioButton4);
        spinner1 = findViewById(R.id.spinner);
        btnRegistrar = findViewById(R.id.button);
        listaView = findViewById(R.id.listaTareas);
        listTareas = new ArrayList<Tareas>();
        //------------------------------------------------------------------------------
        //Parte donde se trabaja el contenido del spinner
        //------------------------------------------------------------------------------
        //Instanciamos
        listCategorias = new ArrayList<>();
        listCategorias.add(new categorias("Trabajo"));
        listCategorias.add(new categorias("Hogar"));
        listCategorias.add(new categorias("Personal"));
        //Rellanamos el spinner con objetos de la clase categoria
        ArrayAdapter<categorias> adapater = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listCategorias);
        adapater.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapater);
        //------------------------------------------------------------------------------
        //Evento del boton
        //------------------------------------------------------------------------------
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rbAlta.isChecked()){
                    prioridad = "Alta";
                } else if (rbMedia.isChecked()) {
                    prioridad = "Media";

                } else if (rbBaja.isChecked()) {
                    prioridad = "Baja";
                }
                tarea = txtTareas.getText().toString();
                txtTareas.getText().clear();
                //Llamando al metodo
                llenarLista(tarea,prioridad,spinner1.getSelectedItem().toString());
            }
        });

        //Parte para el radioButton
        rbAlta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Prioridad de tarea Alta", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rbMedia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Prioridad de tarea Media", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rbBaja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "Prioridadc de tarea baja", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //------------------------------------------------------------------------------
    //Metodo para rellenar la lista
    //------------------------------------------------------------------------------
    public void llenarLista(String tarea, String prioridad, String categorias){

        listTareas.add(new Tareas(tarea,prioridad,categorias));

        Adapatador adapatador = new Adapatador(MainActivity.this,listTareas,R.layout.tareas);
        listaView.setAdapter(adapatador);
    }
}