package com.example.centro_nutricion.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pacientes {

    @PrimaryKey(autoGenerate = true)
    public int idPaciente;

    @ColumnInfo
    public String nombre;

    @ColumnInfo
    public float edad;

    @ColumnInfo
    public float peso;

    @ColumnInfo
    public float estatura;

    @ColumnInfo
    public float resIMC;

    @ColumnInfo
    public int idPlanes;
}
