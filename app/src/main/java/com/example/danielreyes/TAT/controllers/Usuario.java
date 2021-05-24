package com.example.danielreyes.TAT.controllers;

public class Usuario {
    public static String apellido;
    public static String bodega;
    public static String cedula;
    public static String direccion;
    public static String iduser;
    public static String nombre;
    public static String nombres;
    public static String pass;
    public static String telefono;
    public static String rutas;

    public Usuario() {
    }

    public Usuario(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        iduser = str;
        nombre = str2;
        apellido = str3;
        cedula = str4;
        direccion = str5;
        telefono = str6;
        bodega = str7;
        nombres = str9;
        pass = str8;
        rutas = str10;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String str) {
        iduser = str;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String str) {
        nombre = str;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String str) {
        apellido = str;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String str) {
        telefono = str;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String str) {
        bodega = str;
    }

    public static String getNombres() {
        return nombres;
    }

    public static void setNombres(String str) {
        nombres = str;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String str) {
        pass = str;
    }

    public static String getRutas() {
        return rutas;
    }

    public static void setRutas(String rutas) {
        Usuario.rutas = rutas;
    }
}
