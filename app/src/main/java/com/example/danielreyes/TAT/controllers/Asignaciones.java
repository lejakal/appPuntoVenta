package com.example.danielreyes.TAT.controllers;

import java.io.Serializable;

public class Asignaciones implements Serializable {
    private String cliente;
    private String fecha;
    private Integer id;
    private Integer idecliente;
    private String idusuario;
    private Integer ruta;
    private Integer zona;

    public Asignaciones() {
    }

    public Asignaciones(Integer num, Integer num2, String str, String str2, Integer num3, Integer num4, String str3) {
        this.id = num;
        this.idecliente = num2;
        this.cliente = str;
        this.fecha = str2;
        this.ruta = num3;
        this.zona = num4;
        this.idusuario = str3;
    }

    public String getIdusuario() {
        return this.idusuario;
    }

    public void setIdusuario(String str) {
        this.idusuario = str;
    }

    public Integer getIdecliente() {
        return this.idecliente;
    }

    public void setIdecliente(Integer num) {
        this.idecliente = num;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getCliente() {
        return this.cliente;
    }

    public void setCliente(String str) {
        this.cliente = str;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String str) {
        this.fecha = str;
    }

    public Integer getRuta() {
        return this.ruta;
    }

    public void setRuta(Integer num) {
        this.ruta = num;
    }

    public Integer getZona() {
        return this.zona;
    }

    public void setZona(Integer num) {
        this.zona = num;
    }
}
