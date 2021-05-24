package com.example.danielreyes.TAT.controllers;

public class People {
    private String apellido;
    private String barrio;
    private String cedula;
    private String direccion;
    private Integer id;
    private String nombre;
    private String telefono;

    public People() {
    }

    public People(Integer num, String str, String str2, String str3, String str4, String str5, String str6) {
        this.id = num;
        this.nombre = str;
        this.apellido = str2;
        this.cedula = str3;
        this.direccion = str4;
        this.telefono = str5;
        this.barrio = str6;
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

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String str) {
        this.apellido = str;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String str) {
        this.cedula = str;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String str) {
        this.direccion = str;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String str) {
        this.telefono = str;
    }

    public String getBarrio() {
        return this.barrio;
    }

    public void setBarrio(String str) {
        this.barrio = str;
    }
}
