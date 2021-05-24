package com.example.danielreyes.TAT.controllers;

public class Facturas {
    private String cliente;
    private String descripcion;
    private String fechaHora;
    private Integer id;
    private String idFactura;
    private String usuario;
    private String valortotal;

    public Facturas() {
    }

    public Facturas(Integer num, String str, String str2, String str3, String str4, String str5, String str6) {
        this.id = num;
        this.fechaHora = str;
        this.cliente = str2;
        this.usuario = str3;
        this.descripcion = str4;
        this.valortotal = str5;
        this.idFactura = str6;
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

    public String getValortotal() {
        return this.valortotal;
    }

    public void setValortotal(String str) {
        this.valortotal = str;
    }

    public String getIdFactura() {
        return this.idFactura;
    }

    public void setIdFactura(String str) {
        this.idFactura = str;
    }
}
