package com.example.danielreyes.TAT.controllers;

public class ClienteActual {
    public static String apellidos;
    public static String cedula;
    public static String direccion;
    public static String empresa;
    public static Integer id;
    public static String nombres;
    public static String cartera;

    public ClienteActual() {
    }

    public ClienteActual(Integer num, String str, String str2, String str3, String str4, String str5, String str6) {
        id = num;
        nombres = str;
        apellidos = str2;
        cedula = str3;
        direccion = str4;
        empresa = str5;
        cartera = str6;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer num) {
        id = num;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String str) {
        nombres = str;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String str) {
        apellidos = str;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String str) {
        cedula = str;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String str) {
        direccion = str;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String str) {
        empresa = str;
    }

    public String getCartera() {
        return cartera;
    }

    public void setCartera(String strcartera) {
        cartera = strcartera;
    }
}
