package com.example.danielreyes.TAT.controllers;

public class Categorias {
    private Integer id;
    private String nombre;

    public Categorias() {
    }

    public Categorias(Integer num, String str) {
        this.id = num;
        this.nombre = str;
    }

    public Categorias(String str) {
        this.nombre = str;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String str) {
        this.nombre = str;
    }
}
