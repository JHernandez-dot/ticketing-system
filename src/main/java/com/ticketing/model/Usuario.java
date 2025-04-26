package com.ticketing.model;

public class Usuario {
    private String nombre;
    private Rol rol;
    private Departamento departamento;

    public Usuario(String nombre, Rol rol, Departamento departamento) {
        this.nombre = nombre;
        this.rol = rol;
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
