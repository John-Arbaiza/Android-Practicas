package com.example.centro_nutricion.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Planes {
    @PrimaryKey(autoGenerate = true)
    public int idPlanes;

    @ColumnInfo
    public String tipoPlan;

    @ColumnInfo
    public float precio;

    @ColumnInfo
    public String caracteristica;
}
