package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<Usuario> usuarios;

    public Departamento(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
