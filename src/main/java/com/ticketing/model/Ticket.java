package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private int id;
    private Usuario usuario;
    private Departamento departamento;
    private String estado;
    private List<Nota> notas;

    public Ticket(int id, Usuario usuario, Departamento departamento, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.departamento = departamento;
        this.estado = estado;
        this.notas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
  
public Usuario getSolicitante() {
    return this.usuario; 
}


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }
}
