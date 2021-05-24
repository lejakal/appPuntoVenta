package com.example.danielreyes.TAT.controllers;

import java.io.Serializable;

public class Pedidos implements Serializable {
    public static String cedula;
    public static String direccion;
    public static String idFactura;
    public static String valortotal;
    private String cliente;
    private String descripcion;
    private String fechaHora;
    private Integer id;
    private String usuario;

    public Pedidos() {
    }

    public Pedidos(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.id = num;
        this.fechaHora = str;
        this.cliente = str2;
        this.usuario = str3;
        this.descripcion = str4;
        cedula = str5;
        direccion = str6;
        valortotal = str7;
        idFactura = str8;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getFechaHora() {
        return this.fechaHora;
    }

    public void setFechaHora(String str) {
        this.fechaHora = str;
    }

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String str) {
        this.cliente = str;
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

    public static String getValortotal() {
        return valortotal;
    }

    public static void setValortotal(String str) {
        valortotal = str;
    }

    public static String getIdFactura() {
        return idFactura;
    }

    public static void setIdFactura(String str) {
        idFactura = str;
    }
}
