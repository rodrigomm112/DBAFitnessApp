package com.tecnologiasmoviles.dbafitnessapp;

public class NotasRutina {

    private String uid;
    private String Notas;
    private String NombreNota;

    public NotasRutina() {
    }

    public String getNotas() {
        return Notas;
    }

    public void setNotas(String notas) {
        Notas = notas;
    }

    public String getNombreNota() {
        return NombreNota;
    }

    public void setNombreNota(String nombreNota) {
        NombreNota = nombreNota;
    }

    @Override
    public String toString() {
        return NombreNota;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
