package com.example.gestor_tareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        txt1.setText(itemTarea.getTitle());
        txt2.setText(itemTarea.getPrioridad());
        txt3.setText(itemTarea.getCategoria());
        //retornamos la vista
        return vista;
    }
}


