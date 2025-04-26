package com.ticketing.model;

import java.util.ArrayList;

public class Ticket {
    private int id;
    private Usuario solicitante;
    private Departamento departamento;
    private String estado;
    private ArrayList<Nota> notas;

    public Ticket(int id, Usuario solicitante, Departamento departamento, String estadoInicial) {
        this.id = id;
        this.solicitante = solicitante;
        this.departamento = departamento;
        this.estado = estadoInicial;
        this.notas = new ArrayList<>();
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }
}
