package com.example.gestor_tareas;

public class categorias {

    //Definimos las variables o atributos
    public String tarea;

    //Constructor de la clase
    public categorias(String tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {return this.tarea;}
}
