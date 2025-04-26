package com.ticketing.model;

public class Nota {
    private String contenido;
    private Usuario autor;

    public Nota(String contenido, Usuario autor) {
        this.contenido = contenido;
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public Usuario getAutor() {
        return autor;
    }
}
