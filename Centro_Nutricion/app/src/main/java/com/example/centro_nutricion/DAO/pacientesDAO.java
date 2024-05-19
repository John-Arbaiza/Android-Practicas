package com.example.centro_nutricion.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.centro_nutricion.Models.Centro;
import com.example.centro_nutricion.Models.Pacientes;

import java.util.List;

@Dao
public interface pacientesDAO {

    @Query("SELECT * FROM Pacientes")
    List<Pacientes> getPacientes();

    @Transaction
    @Query("SELECT Pacientes.*, Planes.* FROM Pacientes INNER JOIN Planes ON Pacientes.idPlanes = Planes.idPlanes")
    List<Centro> getCentro();

    @Insert
    void insertPacientes(Pacientes pacientes);

    @Update
    void updatePacientes(Pacientes pacientes);

    @Delete
    void deletePacientes(Pacientes pacientes);
}
