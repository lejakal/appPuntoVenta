package com.example.danielreyes.TAT.controllers;

public class Items {
    private String bodega;
    private Integer id;
    private String idsuspend;
    private String iduser;
    private String nombre;
    private String preciocompra;
    private String precioventa;
    private Double stock;

    public Items() {
    }

    public Items(Integer num, String str, Double d, String str2, String str3, String str4, String str5, String str6) {
        this.id = num;
        this.nombre = str;
        this.precioventa = str2;
        this.preciocompra = str3;
        this.bodega = str4;
        this.stock = d;
        this.idsuspend = str5;
        this.iduser = str6;
    }

    public Items(String str, Double d, String str2) {
        this.nombre = str;
        this.precioventa = str2;
        this.stock = d;
    }

    public String getPreciocompra() {
        return this.preciocompra;
    }

    public void setPreciocompra(String str) {
        this.preciocompra = str;
    }

    public String getBodega() {
        return this.bodega;
    }

    public void setBodega(String str) {
        this.bodega = str;
    }

    public String getIduser() {
        return this.iduser;
    }

    public void setIduser(String str) {
        this.iduser = str;
    }

    public String getIdsuspend() {
        return this.idsuspend;
    }

    public void setIdsuspend(String str) {
        this.idsuspend = str;
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

    public String getPrecioventa() {
        return this.precioventa;
    }

    public void setPrecioventa(String str) {
        this.precioventa = str;
    }

    public Double getStock() {
        return this.stock;
    }

    public void setStock(Double d) {
        this.stock = d;
    }
}
