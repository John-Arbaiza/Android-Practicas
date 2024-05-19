package com.example.centro_nutricion.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.centro_nutricion.Models.Planes;
import com.example.centro_nutricion.R;

import java.util.List;

public class planesAdapter extends BaseAdapter {

    //--------------------------------------------
    //Atributos de la clase
    //--------------------------------------------
    public Context context;
    public List<Planes> dataPlanes;
    //-------------------------------------------

    public planesAdapter(Context context, List<Planes> dataPlanes) {
        this.context = context;
        this.dataPlanes = dataPlanes;
    }

    @Override
    public int getCount() {
        return dataPlanes.size();
    }

    @Override
    public Object getItem(int position) {
        return dataPlanes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_planes,null);
        //Asociamos los id
        TextView txtTipoPlanes = convertView.findViewById(R.id.txtTipo);
        TextView txtPre = convertView.findViewById(R.id.txtPrecio);
        TextView txtDes = convertView.findViewById(R.id.txtDescripcion);
        Planes planes = new Planes();
        planes = dataPlanes.get(position);
        txtTipoPlanes.setText(planes.tipoPlan);
        txtPre.setText(String.valueOf(planes.precio));
        txtDes.setText(planes.caracteristica);

        return convertView;
    }
}
