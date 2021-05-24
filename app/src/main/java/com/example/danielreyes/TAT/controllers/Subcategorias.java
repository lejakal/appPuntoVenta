package com.example.danielreyes.TAT.controllers;

public class Subcategorias {
    private Integer id;
    private String nombre;

    public Subcategorias() {
    }

    public Subcategorias(Integer num, String str) {
        this.id = num;
        this.nombre = str;
    }

    public Subcategorias(String str) {
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
