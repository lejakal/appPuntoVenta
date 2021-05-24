package com.example.danielreyes.TAT.controllers;

public class PedidoActual {
    public static String cedula;
    public static String cliente;
    public static String direccion;
    public static Integer id;
    private String descripcion;
    private String fechaHora;
    private String usuario;

    public PedidoActual() {
    }

    public PedidoActual(Integer num, String str, String str2, String str3, String str4, String str5, String str6) {
        id = num;
        this.fechaHora = str;
        cliente = str2;
        this.usuario = str3;
        this.descripcion = str4;
        cedula = str5;
        direccion = str6;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer num) {
        id = num;
    }

    public String getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(String str) {
        this.fechaHora = str;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String str) {
        cliente = str;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String str) {
        this.usuario = str;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String str) {
        this.descripcion = str;
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
}
