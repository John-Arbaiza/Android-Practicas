package com.example.centro_nutricion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.centro_nutricion.DAO.PlanDAO;
import com.example.centro_nutricion.DAO.pacientesDAO;
import com.example.centro_nutricion.Models.Planes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    //---------------------------------------------------------
    //Establecemos los atributos de la clase
    //---------------------------------------------------------
    //Relacionados con el fragment
     public Fragment inicio,pacientes, planes ,listadoPa;
     public BottomNavigationView menu;
    public Button btnVisualizar;
    public ImageView imglist;
     //---------------------------------------------------------
    //Relacionados con la DB
    public appDataBase db;
    public pacientesDAO consulta;
    public PlanDAO plandao;
    //-----------------------------------------------------------

    @SuppressLint("MissingInflatedId")
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

        //---------------------------------------------------------
        //Parte donde se trabaja la base de datos
        db = Room.databaseBuilder(getApplicationContext(),appDataBase.class,"dbCentroNutricion").allowMainThreadQueries().build();
        consulta = db.getPacientesdao();
        plandao = db.getPlanesdao();
        Planes basico = new Planes();
        basico.idPlanes = 1;
        basico.tipoPlan = "Básico";
        basico.precio = 15.00f;
        basico.caracteristica = "Plan de alimentación";
        Planes Medio = new Planes();
        Medio.idPlanes = 2;
        Medio.tipoPlan = "Medio";
        Medio.precio = 20.00f;
        Medio.caracteristica = "Plan de alimentación y Asesoria de entrenamiento";
        Planes Premium = new Planes();
        Premium.idPlanes = 3;
        Premium.tipoPlan = "Premium";
        Premium.precio = 30.00f;
        Premium.caracteristica = "Plan de alimentación, Entrenamiento y Asesoria de un nutricionista";
        if(plandao.getPlanes().isEmpty()){
            plandao.insertPlanes(basico);
            plandao.insertPlanes(Medio);
            plandao.insertPlanes(Premium);}
        else {
            Toast.makeText(this, "Una buena alimentación siempre es lo mejor", Toast.LENGTH_SHORT).show();
        }
        //----------------------------------------------------------
        //Relacionamos lod id
        //----------------------------------------------------------
        menu = findViewById(R.id.menuOpciones);
        btnVisualizar = findViewById(R.id.btnVerPaciente);
        imglist = findViewById(R.id.imageView2);
        //----------------------------------------------------------
        imglist.setVisibility(View.INVISIBLE);
        btnVisualizar.setVisibility(View.INVISIBLE);
        //----------------------------------------------------------
        //Parte donde se trabaja lo de los Fragment
        //----------------------------------------------------------
        inicio = new inicioFragment();
        pacientes = new pacientesFragment();
        planes = new planFragment();
        listadoPa = new listadoPacientesFragment();
        //-----------------------------------------------------------
        //parte donde se trabaja el evento del menu y otros
        //-----------------------------------------------------------
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.inicio:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,inicio).commit();
                        btnVisualizar.setVisibility(View.INVISIBLE);
                        imglist.setVisibility(View.INVISIBLE);
                        break;

                    case R.id.pacientes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,pacientes).commit();
                        btnVisualizar.setVisibility(View.VISIBLE);
                        imglist.setVisibility(View.VISIBLE);
                        break;

                    case R.id.plan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,planes).commit();
                        btnVisualizar.setVisibility(View.INVISIBLE);
                        imglist.setVisibility(View.INVISIBLE);
                        break;
                }
                return true;
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,listadoPa).commit();
                btnVisualizar.setVisibility(View.INVISIBLE);
                imglist.setVisibility(View.INVISIBLE);
            }
        });

    }
}