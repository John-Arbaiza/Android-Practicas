package com.example.centro_nutricion.Adapter;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.centro_nutricion.DAO.pacientesDAO;
import com.example.centro_nutricion.Models.Pacientes;
import com.example.centro_nutricion.R;
import com.example.centro_nutricion.appDataBase;
import com.example.centro_nutricion.listadoPacientesFragment;

import java.util.ArrayList;
import java.util.List;

public class pacientesAdapter extends BaseAdapter {

    public Context context;
    public List<Pacientes> dataPacientes;
    public appDataBase db;
    public pacientesDAO pacientesdao;
    public Double res;
    public listadoPacientesFragment pacientesListado;
    //Constructor
    public pacientesAdapter(Context context, List<Pacientes> dataPacientes) {
        this.context = context;
        this.dataPacientes = dataPacientes;
    }

    public pacientesAdapter(listadoPacientesFragment pacientesListado) {
        this.pacientesListado = pacientesListado;
    }

    @Override
    public int getCount() {
        return dataPacientes.size();
    }

    @Override
    public Object getItem(int position) {
        return dataPacientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_pacientes,null);
        //Asociamos los id
        EditText  paciente = convertView.findViewById(R.id.lblPacientes);
        EditText edad = convertView.findViewById(R.id.lblEdades);
        EditText peso = convertView.findViewById(R.id.lblPesoPaciente);
        EditText estatura = convertView.findViewById(R.id.lblAlturas);
        EditText imc = convertView.findViewById(R.id.lblIMC);
        EditText plan = convertView.findViewById(R.id.lblIDPlan);
        ImageButton btnElimina = convertView.findViewById(R.id.btnDelete);
        ImageButton btnEdita = convertView.findViewById(R.id.btnEdit);
        ImageButton btnSaveEdit = convertView.findViewById(R.id.btnGuardarUpdate);
        ImageButton btnCalculadora = convertView.findViewById(R.id.btnCalculadora);
        //Pacientes pacientes = new Pacientes();
        Pacientes pacientes = dataPacientes.get(position);
        paciente.setText(pacientes.nombre);
        edad.setText(String.valueOf(pacientes.edad));
        peso.setText(String.valueOf(pacientes.peso));
        estatura.setText(String.valueOf(pacientes.estatura));
        imc.setText(String.valueOf(pacientes.resIMC));
        plan.setText(String.valueOf(pacientes.idPlanes));
        //----------------------------------------------------------
        //Desactivamos que se pueda editar en los EditText
        //---------------------------------------------------------
        paciente.setEnabled(false);
        paciente.setInputType(InputType.TYPE_NULL);
        edad.setEnabled(false);
        edad.setInputType(InputType.TYPE_NULL);
        peso.setEnabled(false);
        peso.setInputType(InputType.TYPE_NULL);
        estatura.setEnabled(false);
        estatura.setInputType(InputType.TYPE_NULL);
        imc.setEnabled(false);
        imc.setInputType(InputType.TYPE_NULL);
        plan.setEnabled(false);
        plan.setInputType(InputType.TYPE_NULL);
        //----------------------------------------------------------
        //Eventos
        //-----------------------------------------------------------
        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    db = Room.databaseBuilder(context.getApplicationContext(),appDataBase.class,"dbCentroNutricion").allowMainThreadQueries().build();
                    pacientesdao = db.getPacientesdao();
                    pacientesdao.deletePacientes(pacientes);
                    dataPacientes.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Paciente Eliminado con exito", Toast.LENGTH_SHORT).show();
            }
        });

        btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------------------------------------------------
                //Activamos la edicion  en los EditText
                //---------------------------------------------------------
                paciente.setEnabled(true);
                paciente.setInputType(InputType.TYPE_CLASS_TEXT);
                edad.setEnabled(true);
                edad.setInputType(InputType.TYPE_CLASS_TEXT);
                /*peso.setEnabled(true);
                peso.setInputType(InputType.TYPE_CLASS_TEXT);
                estatura.setEnabled(true);
                estatura.setInputType(InputType.TYPE_CLASS_TEXT);
                plan.setEnabled(true);
                plan.setInputType(InputType.TYPE_CLASS_TEXT);*/
                //-----------------------------------------------------------
            }
        });

        btnCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = Room.databaseBuilder(context.getApplicationContext(),appDataBase.class,"dbCentroNutricion").allowMainThreadQueries().build();
                pacientesdao = db.getPacientesdao();
                pacientes.nombre = paciente.getText().toString();
                pacientes.edad = Integer.parseInt(edad.getText().toString());
                //----------------------------------------------------------------------------
                pacientesdao.updatePacientes(pacientes);
                dataPacientes.add(pacientes);
                notifyDataSetChanged();
                Toast.makeText(context, "Dato Atualizado con exito", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
