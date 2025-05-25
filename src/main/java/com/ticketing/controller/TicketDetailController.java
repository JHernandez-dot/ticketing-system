package com.ticketing.controller;

import com.ticketing.model.Nota;
import com.ticketing.model.Ticket;
import com.ticketing.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TicketDetailController {

    @FXML
    private Label idLabel;
    @FXML
    private Label estadoLabel;
    @FXML
    private TextArea notasArea;
    @FXML
    private TextField nuevaNotaField;

    private Ticket ticket;

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        mostrarDetalles();
    }

    private void mostrarDetalles() {
        idLabel.setText(String.valueOf(ticket.getId()));
        estadoLabel.setText(ticket.getEstado());
        notasArea.clear();
        for (Nota nota : ticket.getNotas()) {
            notasArea.appendText(nota.getContenido() + " - " + nota.getAutor().getNombre() + "\n");
        }
    }

    @FXML
    private void handleAgregarNota() {
        String texto = nuevaNotaField.getText();
        if (!texto.isBlank()) {
            Usuario autor = ticket.getUsuario(); // o usuario logueado
            Nota nueva = new Nota(texto, autor);
            ticket.agregarNota(nueva);
            notasArea.appendText(nueva.getContenido() + " - " + autor.getNombre() + "\n");
            nuevaNotaField.clear();
        }
    }

    @FXML
    private void handleCambiarEstado() {
        String nuevoEstado = ticket.getEstado().equals("Pendiente") ? "En Proceso" : "Cerrado";
        ticket.setEstado(nuevoEstado);
        estadoLabel.setText(nuevoEstado);
    }
    @FXML
private void handleViewDetails() {
    // Lógica para mostrar detalles del ticket
    System.out.println("Ver detalles del ticket");
}

@FXML
private void handleChangeStatus() {
    // Lógica para cambiar el estado del ticket
    System.out.println("Cambiar estado del ticket");
}

@FXML
private void handleAddNote() {
    // Lógica para agregar nota al ticket
    System.out.println("Agregar nota al ticket");
}

}
