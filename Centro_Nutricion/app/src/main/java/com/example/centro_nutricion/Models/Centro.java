package com.example.centro_nutricion.Models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class Centro {
    @Embedded public Planes planes;

    @Relation(
            parentColumn = "idPlanes",
            entityColumn = "idPlanes"
    )

    public Pacientes pacientes;
}
