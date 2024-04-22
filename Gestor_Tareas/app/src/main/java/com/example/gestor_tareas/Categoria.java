package com.example.gestor_tareas;

public class Categoria {

    //Atributos de la clase
    public String tarea;
    public int img;
    public int color;

    //Constructor de la clase
    public Categoria(String tarea, int img, int color) {
        this.tarea = tarea;
        this.img = img;
        this.color = color;
    }

    //getter and setter
    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.getTarea();
    }
}
