package com.ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<String> parametros;
    private List<String> estadosTicket;
    private List<String> flujosTrabajo;

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

    public List<String> getParametros() {
        return parametros;
    }

    public List<String> getEstadosTicket() {
        return estadosTicket;
    }

    public List<String> getFlujosTrabajo() {
        return flujosTrabajo;
    }
}
