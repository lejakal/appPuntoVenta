package com.example.danielreyes.TAT.controllers;

public class Customer {
    private String apellidos;
    private String cedula;
    private String direccion;
    private String empresa;
    private Integer id;
    private String nombres;
    private String telefono;
    public static String cartera;

    public Customer() {
    }

    public Customer(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = num;
        this.nombres = str;
        this.apellidos = str2;
        this.cedula = str3;
        this.direccion = str4;
        this.empresa = str5;
        this.telefono = str6;
        this.cartera = str7;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String str) {
        this.nombres = str;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String str) {
        this.apellidos = str;
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

    public String getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(String str) {
        this.empresa = str;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String str) {
        this.telefono = str;
    }

    public String getCartera() {
        return cartera;
    }

    public void setCartera(String cartera) {
        Customer.cartera = cartera;
    }
}
