package com.ticketing.model;

import java.util.ArrayList;

public class Sistema {
    private ArrayList<String> parametros;
    private ArrayList<String> estadosTicket;
    private ArrayList<String> flujosTrabajo;

    public Sistema() {
        this.parametros = new ArrayList<>();
        this.estadosTicket = new ArrayList<>();
        this.flujosTrabajo = new ArrayList<>();
    }

    public void agregarParametro(String parametro) {
        parametros.add(parametro);
    }

    public void agregarEstadoTicket(String estado) {
        estadosTicket.add(estado);
    }

    public void agregarFlujoTrabajo(String flujo) {
        flujosTrabajo.add(flujo);
    }
}
