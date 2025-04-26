package com.ticketing.model;

import java.util.ArrayList;

public class Rol {
    private String nombre;
    private ArrayList<String> permisos;

    public Rol(String nombre) {
        this.nombre = nombre;
        this.permisos = new ArrayList<>();
    }

    public void agregarPermiso(String permiso) {
        permisos.add(permiso);
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getPermisos() {
        return permisos;
    }
}
