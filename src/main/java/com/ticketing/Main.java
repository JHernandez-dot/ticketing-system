package com.ticketing;

import com.ticketing.model.*;
import com.ticketing.service.GestorDeTickets;

public class Main {
    public static void main(String[] args) {
        // Crear sistema y configuraciones
        Sistema sistema = new Sistema();
        sistema.agregarParametro("soporte_horario: 9am - 6pm");
        sistema.agregarEstadoTicket("Pendiente");
        sistema.agregarEstadoTicket("En Proceso");
        sistema.agregarEstadoTicket("Cerrado");

        // Crear rol y usuario
        Rol admin = new Rol("Administrador");
        admin.agregarPermiso("GESTIONAR_TICKETS");

        Departamento departamentoIT = new Departamento("Soporte Técnico");
        Usuario usuario1 = new Usuario("Carlos", admin, departamentoIT);
        departamentoIT.agregarUsuario(usuario1);

        // Crear ticket
        Ticket ticket1 = new Ticket(1, usuario1, departamentoIT, "Pendiente");
        Nota nota1 = new Nota("Se necesita revisar el servidor", usuario1);
        ticket1.agregarNota(nota1);

        // Registrar ticket
        GestorDeTickets gestor = GestorDeTickets.getInstance();
        gestor.registrarTicket(ticket1);

        // Mostrar tickets pendientes
        System.out.println("Tickets Pendientes:");
        for (Ticket t : gestor.obtenerTicketsPendientes()) {
            System.out.println("Ticket #" + t.getId() + " - Estado: " + t.getEstado());
        }
    }
}
