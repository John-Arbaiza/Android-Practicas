package com.example.centro_nutricion;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.centro_nutricion.Adapter.pacientesAdapter;
import com.example.centro_nutricion.DAO.pacientesDAO;
import com.example.centro_nutricion.Models.Pacientes;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link listadoPacientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class listadoPacientesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //---------------------------------------------------------
    public appDataBase db;
    public pacientesDAO pacientesdao;
    public pacientesAdapter adapter;
    public ListView listado;
    private MainActivity principal;

    //----------------------------------------------------------

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public listadoPacientesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listadoPacientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static listadoPacientesFragment newInstance(String param1, String param2) {
        listadoPacientesFragment fragment = new listadoPacientesFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = Room.databaseBuilder(getContext(),appDataBase.class,"dbCentroNutricion").allowMainThreadQueries().build();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_listado_pacientes, container, false);
        //---------------------------------------------------------------------------------------------
        listado = root.findViewById(R.id.listadoPaciente);
        //---------------------------------------------------------------------------------------------
        pacientesdao = db.getPacientesdao();
        List<Pacientes> listPacientesRegistrado = pacientesdao.getPacientes();
        for(Pacientes itemPaciente:listPacientesRegistrado){
            Log.d("Pacientes ->",itemPaciente.nombre);
        }
        principal = (MainActivity) getActivity();
        //Parte donde trabaja  para que el listView muestre
       adapter = new pacientesAdapter(getActivity(),listPacientesRegistrado);
       listado.setAdapter(adapter);
       adapter.notifyDataSetChanged();

        return root;
    }

    //public void eliminarPaciente(Pacientes pacientes){
     //   pacientesdao.deletePacientes(pacientes);
    //    Toast.makeText(getActivity(), "Paciente Eliminado con exito", Toast.LENGTH_SHORT).show();
    //}
}