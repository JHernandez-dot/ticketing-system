/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ticketing.dao;

import com.ticketing.db.ConexionDB;
import com.ticketing.model.Ticket;
import com.ticketing.model.Usuario;
import com.ticketing.model.Departamento;
import com.ticketing.model.Nota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private NotaDAO notaDAO = new NotaDAO();

    public void insertar(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO tickets (id, id_solicitante, id_departamento, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticket.getId());
            ps.setInt(2, obtenerIdUsuario(ticket.getSolicitante().getNombre(), conn));
            ps.setInt(3, obtenerIdDepartamento(ticket.getDepartamento().getNombre(), conn));
            ps.setString(4, ticket.getEstado());
            ps.executeUpdate();

            // Insertar notas asociadas
            for (Nota nota : ticket.getNotas()) {
                notaDAO.insertar(nota, ticket.getId());
            }
        }
    }

    private int obtenerIdUsuario(String nombreUsuario, Connection conn) throws SQLException {
        String sql = "SELECT id FROM usuarios WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
            else throw new SQLException("Usuario no encontrado: " + nombreUsuario);
        }
    }

    private int obtenerIdDepartamento(String nombreDepartamento, Connection conn) throws SQLException {
        String sql = "SELECT id FROM departamentos WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreDepartamento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
            else throw new SQLException("Departamento no encontrado: " + nombreDepartamento);
        }
    }

    public Ticket buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario solicitante = usuarioDAO.buscarPorId(rs.getInt("id_solicitante"));
                Departamento departamento = departamentoDAO.buscarPorId(rs.getInt("id_departamento"));
                Ticket ticket = new Ticket(rs.getInt("id"), solicitante, departamento, rs.getString("estado"));
                List<Nota> notas = notaDAO.listarPorTicketId(id);
                for (Nota nota : notas) {
                    ticket.agregarNota(nota);
                }
                return ticket;
            }
        }
        return null;
    }

    public List<Ticket> buscarPorEstado(String estado) throws SQLException {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE estado = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario solicitante = usuarioDAO.buscarPorId(rs.getInt("id_solicitante"));
                Departamento departamento = departamentoDAO.buscarPorId(rs.getInt("id_departamento"));
                Ticket ticket = new Ticket(rs.getInt("id"), solicitante, departamento, rs.getString("estado"));
                List<Nota> notas = notaDAO.listarPorTicketId(rs.getInt("id"));
                for (Nota nota : notas) {
                    ticket.agregarNota(nota);
                }
                lista.add(ticket);
            }
        }
        return lista;
    }
}
