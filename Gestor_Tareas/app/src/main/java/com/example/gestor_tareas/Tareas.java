package com.example.gestor_tareas;

public class Tareas {

    //Atributos de la clase
    public String titulo;
    public String categoria;
    public String prioridad;
    private  Categoria categorias;

    //Constructor de la clase
    public Tareas(String titulo, String categoria, String prioridad, Categoria categorias) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.categorias = categorias;
    }

    public Tareas() {
    }

    //Getter and Setter
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Categoria getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = categorias;
    }
}
