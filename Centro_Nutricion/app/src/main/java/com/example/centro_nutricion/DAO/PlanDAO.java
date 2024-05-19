package com.example.centro_nutricion.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.centro_nutricion.Models.Planes;

import java.util.List;

@Dao
public interface PlanDAO {
    @Query("SELECT * FROM Planes")
    List<Planes> getPlanes();

    @Insert
    void insertPlanes(Planes planes);
}
