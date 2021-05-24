package com.example.danielreyes.TAT.controllers;

public class Pedidosdtl {
    private String cedula;
    private String cliente;
    private String descripcion;
    private String direccion;
    private String fechaHora;
    private Integer id;
    private String pago;
    private String usuario;

    public Pedidosdtl() {
    }

    public Pedidosdtl(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = num;
        this.fechaHora = str;
        this.cliente = str2;
        this.usuario = str3;
        this.descripcion = str4;
        this.cedula = str5;
        this.direccion = str6;
        this.pago = str7;
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

    public String getPago() {
        return this.pago;
    }

    public void setPago(String str) {
        this.pago = str;
    }
}
