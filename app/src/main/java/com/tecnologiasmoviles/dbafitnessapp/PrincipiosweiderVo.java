package com.tecnologiasmoviles.dbafitnessapp;

public class PrincipiosweiderVo {
    private String nombre;
    private int info;
    private int foto;

    public PrincipiosweiderVo(){

    }

    public PrincipiosweiderVo(String nombre, int info, int foto) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
