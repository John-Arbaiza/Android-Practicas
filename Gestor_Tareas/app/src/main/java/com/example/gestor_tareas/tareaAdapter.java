package com.example.gestor_tareas;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class tareaAdapter extends BaseAdapter {

    //Atributos de la clase
    public Context context;
    public ArrayList<Tareas> dataTareas;
    private MainActivity pricipal;

    //Constructor de la clase
    public tareaAdapter(Context context, ArrayList<Tareas> dataTareas, MainActivity pricipal) {
        this.context = context;
        this.dataTareas = dataTareas;
        this.pricipal = pricipal;
    }

    @Override
    public int getCount() {
        return this.dataTareas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataTareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_tareas,null);
        Tareas tareaItem = dataTareas.get(position);
        //Asoiamos los elemntos
        TextView lblTitulo = convertView.findViewById(R.id.textView5);
        TextView lblPrioridad = convertView.findViewById(R.id.textView6);
        TextView lblCategoria = convertView.findViewById(R.id.textView7);
        ImageView imgCategoria = convertView.findViewById(R.id.imageView2);
        ImageButton btnDelete = convertView.findViewById(R.id.imageButton);
        ImageButton btnEdit = convertView.findViewById(R.id.imageButton2);
        //Asignamos los valores a mostrar
        lblTitulo.setText(tareaItem.getTitulo());
        lblPrioridad.setText(tareaItem.getPrioridad());
        lblCategoria.setText(tareaItem.getCategoria());
        //Definimos el color y la imagen a mostrar
        imgCategoria.setImageResource(tareaItem.getCategorias().getImg());
        imgCategoria.setColorFilter(context.getResources().getColor(tareaItem.getCategorias().getColor()));

        //Evento para eliminar la tarea
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamamos al metod
                AlertDialog dialogo = alertaDelete(position);
                dialogo.show();

            }
        });

        return convertView;
    }

    public AlertDialog alertaDelete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Eliminar Tarea").setMessage("Seguro de eliminar esta tarea")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         dataTareas.remove(position);
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
