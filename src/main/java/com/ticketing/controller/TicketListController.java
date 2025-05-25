package com.ticketing.controller;

import com.ticketing.model.Ticket;
import com.ticketing.service.GestorDeTickets;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketListController implements Initializable {

    @FXML
    private TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, Integer> idColumn;

    @FXML
    private TableColumn<Ticket, String> estadoColumn;

    @FXML
    private TableColumn<Ticket, String> solicitanteColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        solicitanteColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getSolicitante().getNombre()
            )
        );

        ticketTable.getItems().addAll(GestorDeTickets.getInstance().obtenerTodosLosTickets());
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
