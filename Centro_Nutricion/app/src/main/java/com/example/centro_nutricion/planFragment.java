package com.example.centro_nutricion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.centro_nutricion.Adapter.planesAdapter;
import com.example.centro_nutricion.DAO.PlanDAO;
import com.example.centro_nutricion.Models.Planes;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link planFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class planFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //----------------------------------------------------
    //Definiendo los atributos a emplear
    //----------------------------------------------------
    public appDataBase db;
    public PlanDAO plandao;

    public ListView listPlanes;
    private MainActivity principal;
    public planesAdapter adapter;
    //------------------------------------------------------

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public planFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment planFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static planFragment newInstance(String param1, String param2) {
        planFragment fragment = new planFragment();
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
        View root = inflater.inflate(R.layout.fragment_plan,container,false);
        plandao = db.getPlanesdao();
        List<Planes> listPlan = plandao.getPlanes();
        for (Planes itemPlanes : listPlan){
            Log.d("Plan -> ",itemPlanes.tipoPlan);
        }

        //-----------------------------------------------------
        //Parte que se encargue de mostrar en el listView
        //------------------------------------------------------
        listPlanes = root.findViewById(R.id.listaPlanes);
        principal =(MainActivity) getActivity();
        adapter = new planesAdapter(getActivity(),listPlan);
        listPlanes.setAdapter(adapter);
        return root;
    }
}