package com.example.centro_nutricion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.centro_nutricion.Adapter.planesAdapter;
import com.example.centro_nutricion.DAO.PlanDAO;
import com.example.centro_nutricion.DAO.pacientesDAO;
import com.example.centro_nutricion.Models.Pacientes;
import com.example.centro_nutricion.Models.Planes;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pacientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pacientesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //-------------------------------------------------------
    //Atributos de la clase
    //-------------------------------------------------------
    public appDataBase db;
    public pacientesDAO pacientesdao;
    public PlanDAO planesdao;
    public EditText txtName, txtAge,txtEstatura, txtPeso;
    public Spinner spPlanes;
    public TextView txtIMCRes,txtEstado;
    public Button btnCalcular, btnIngresar,btnVer;

    public Double res;
    //-------------------------------------------------------

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pacientesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pacientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static pacientesFragment newInstance(String param1, String param2) {
        pacientesFragment fragment = new pacientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = Room.databaseBuilder(getContext(),appDataBase.class,"dbCentroNutricion").allowMainThreadQueries().build();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pacientes,container,false);
        //----------------------------------------------------------------------------------
        //Asociamos lod id
        //----------------------------------------------------------------------------------
        txtName = root.findViewById(R.id.txtNombre);
        txtAge = root.findViewById(R.id.txtEdad);
        txtPeso = root.findViewById(R.id.txtPeso);
        txtEstatura = root.findViewById(R.id.editTextText5);
        txtIMCRes = root.findViewById(R.id.txtRESIMC);
        txtEstado = root.findViewById(R.id.txtEstado);
        spPlanes = root.findViewById(R.id.spinner);
        btnCalcular = root.findViewById(R.id.btnCalcular);
        btnIngresar = root.findViewById(R.id.btnIngresar);
        //btnVer = root.findViewById(R.id.btnPacientes);
        //------------------------------------------------------------------------------------
        //Parte que se encarga del spinner
        //------------------------------------------------------------------------------------
        List<String> mylist = new ArrayList<>();
        planesdao = db.getPlanesdao();
        List<Planes> listPlan = planesdao.getPlanes();
        for (Planes itemPlanes : listPlan){
            //Log.d("Plan -> ",itemPlanes.tipoPlan);
            mylist.add(itemPlanes.tipoPlan);
        }
        String[] planesCentro = {mylist.get(0),mylist.get(1),mylist.get(2)};
        ArrayAdapter adapter1 = new ArrayAdapter<>(getActivity(),androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,planesCentro);
        spPlanes.setAdapter(adapter1);
        //-------------------------------------------------------------------------------------
        //Eventos de los buttons
        //-------------------------------------------------------------------------------------
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //----------------------------------------------------------------------------
                if(txtPeso.getText().toString().isEmpty() && txtEstatura.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debe ingresar los datos requeridos", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Definimos las variables a emplear
                    Double altura = Double.parseDouble(txtEstatura.getText().toString());
                    Double peso = Double.parseDouble(txtPeso.getText().toString());
                    //Hacemos uso de la clae Math para elevar y redondear
                    Double elevarEstatura = Math.pow(altura,2);
                    Double IMC = peso / elevarEstatura;
                    res = Math.round(IMC * 100.0) / 100.0;
                    txtIMCRes.setText(res.toString());
                    //Determinamos el estado de salud
                    if(res < 18.5){
                        txtEstado.setText("Bajo Peso");
                    } else if (18.5 <= res && res < 25 ) {
                        txtEstado.setText("Peso Normal");
                    } else if (25 <= res && res < 30) {
                        txtEstado.setText("Sobre Peso");
                    } else if (res >= 30) {
                        txtEstado.setText("Obesidad");
                    }

                }
                //----------------------------------------------------------------------------
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pacientesdao = db.getPacientesdao();
                Pacientes pacientes = new Pacientes();
                pacientes.nombre = txtName.getText().toString();
                pacientes.edad = Integer.parseInt(txtAge.getText().toString());
                pacientes.peso = Float.parseFloat(txtPeso.getText().toString());
                pacientes.estatura = Float.parseFloat(txtEstatura.getText().toString());
                pacientes.resIMC = Float.parseFloat(res.toString());
                planesdao = db.getPlanesdao();
                int id = 0;
                switch (spPlanes.getSelectedItem().toString()){
                    case "Básico":
                        id = 1;
                        break;

                    case "Medio":
                        id = 2;
                        break;

                    case "Premium":
                        id = 3;
                        break;

                    default:
                        System.out.println("plan desconocido");
                }
                pacientes.idPlanes = id;
                pacientesdao.insertPacientes(pacientes);
                Toast.makeText(getContext(), "Paciente Registrado con exito", Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }
}