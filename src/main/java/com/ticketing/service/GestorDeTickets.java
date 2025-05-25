package com.ticketing.service;

import com.ticketing.model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class GestorDeTickets {

    private static GestorDeTickets instance;
    private List<Ticket> tickets;

    private GestorDeTickets() {
        tickets = new ArrayList<>();
    }

    public static GestorDeTickets getInstance() {
        if (instance == null) {
            instance = new GestorDeTickets();
        }
        return instance;
    }

    public void registrarTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> obtenerTickets() {
        return tickets;
    }

    public List<Ticket> obtenerTicketsPendientes() {
        List<Ticket> pendientes = new ArrayList<>();
        for (Ticket t : tickets) {
            if ("Pendiente".equalsIgnoreCase(t.getEstado())) {
                pendientes.add(t);
            }
        }
        return pendientes;
    }
    public List<Ticket> obtenerTodosLosTickets() {
    return this.tickets; 
}

}
