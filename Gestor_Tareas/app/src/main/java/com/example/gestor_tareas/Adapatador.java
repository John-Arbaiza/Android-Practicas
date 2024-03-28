package com.example.gestor_tareas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapatador  extends BaseAdapter {

    //Definimos los atribitos de la clase
    Context context;
    public ArrayList<Tareas> categoriaTArea;
    public  int contenedor;

    //Constructor
    public Adapatador(Context context, ArrayList<Tareas> categoriaTArea, int contenedor) {
        this.context = context;
        this.categoriaTArea = categoriaTArea;
        this.contenedor = contenedor;
    }

    @Override
    public int getCount() {
       return  categoriaTArea.size();
    }

    @Override
    public Object getItem(int position) {

       return categoriaTArea.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //creamos un objeto view
        View vista = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        //Asociamos la vista al adaptador
        vista = layoutInflater.inflate(R.layout.tareas,null);
        //Asociamos los elementos
        Tareas itemTarea = categoriaTArea.get(position);
        //Mostramos los datos de los objetos
        TextView txt1 = vista.findViewById(R.id.textView5);
        TextView txt2 = vista.findViewById(R.id.textView6);
        TextView txt3 = vista.findViewById(R.id.textView7);
        ImageButton btnEliminar = vista.findViewById(R.id.imageButton);

        txt1.setText(itemTarea.getTitle());
        txt2.setText(itemTarea.getPrioridad());
        txt3.setText(itemTarea.getCategoria());

        //Definimos el evento del button  encargado de eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instanciamos un objeto de tipo AlertDialog
                AlertDialog alerta = alertaEliminar(position);
                alerta.show();

            }
        });

        //retornamos la vista
        return vista;

    }

    //Definimos un AlertDialog
    public AlertDialog alertaEliminar(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Eliminar Tarea").setMessage("Seguro que desea eliminar esta tarea")
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categoriaTArea.remove(position);
                        notifyDataSetChanged();
                        
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Tarea no eliminada", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }

}




