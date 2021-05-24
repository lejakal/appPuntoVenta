package com.example.danielreyes.TAT.controllers;
import java.io.Serializable;

public class Zonas implements Serializable {

    private String descripcion;
    private Integer id;
    private String idUser;
    private String imagen;
    private String nombre;

    public Zonas() {
    }

    public Zonas(Integer num, String str, String str2, String str3) {
        setId(num);
        setNombre(str);
        setDescripcion(str2);
        setidUser(str3);
    }

    public String getidUser() {
        return this.idUser;
    }

    public void setidUser(String str) {
        this.idUser = str;
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

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String str) {
        this.descripcion = str;
    }

    public String getImage() {
        return this.imagen;
    }

    public void setImagen(String str) {
        this.imagen = str;
    }
}
