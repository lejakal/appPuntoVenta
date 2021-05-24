package com.example.danielreyes.TAT.controllers;

public class PedidoItems {
    private String bodega;
    private String cantidad;
    private String compra;
    private String iditem;
    private String idsuspend;
    private String nombreitem;
    private String venta;

    public PedidoItems() {
    }

    public PedidoItems(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.iditem = str;
        this.idsuspend = str2;
        this.nombreitem = str3;
        this.cantidad = str4;
        this.compra = str5;
        this.venta = str6;
        this.bodega = str7;
    }

    public String getIdsuspend() {
        return this.idsuspend;
    }

    public void setIdsuspend(String str) {
        this.idsuspend = str;
    }

    public String getIditem() {
        return this.iditem;
    }

    public void setIditem(String str) {
        this.iditem = str;
    }

    public String getNombreitem() {
        return this.nombreitem;
    }

    public void setNombreitem(String str) {
        this.nombreitem = str;
    }

    public String getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(String str) {
        this.cantidad = str;
    }

    public String getCompra() {
        return this.compra;
    }

    public void setCompra(String str) {
        this.compra = str;
    }

    public String getVenta() {
        return this.venta;
    }

    public void setVenta(String str) {
        this.venta = str;
    }

    public String getBodega() {
        return this.bodega;
    }

    public void setBodega(String str) {
        this.bodega = str;
    }
}
