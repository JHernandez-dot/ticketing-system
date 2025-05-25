package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Rol {
    private String nombre;
    private List<String> permisos;

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

    public List<String> getPermisos() {
        return permisos;
    }
}
