package com.example.centro_nutricion;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.centro_nutricion.DAO.PlanDAO;
import com.example.centro_nutricion.DAO.pacientesDAO;
import com.example.centro_nutricion.Models.Pacientes;
import com.example.centro_nutricion.Models.Planes;

@Database(entities = {Pacientes.class, Planes.class}, version = 1)
public abstract class appDataBase extends RoomDatabase {
    public abstract pacientesDAO getPacientesdao();
    public abstract PlanDAO getPlanesdao();
}
