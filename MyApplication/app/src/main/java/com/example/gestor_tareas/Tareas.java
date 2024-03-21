package com.example.gestor_tareas;

public class Tareas {

    //Definimos las variables o atributos de la clase
    private String title;
    private String prioridad;
    private String categoria;

    //Constructor de la clase
    public Tareas(String title, String prioridad, String categoria) {
        this.title = title;
        this.prioridad = prioridad;
        this.categoria = categoria;
    }

    //Metodos getter and setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
