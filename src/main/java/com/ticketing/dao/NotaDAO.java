package com.ticketing.dao;

import com.ticketing.db.ConexionDB;
import com.ticketing.model.Nota;
import com.ticketing.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void insertar(Nota nota, int idTicket) throws SQLException {
        String sql = "INSERT INTO notas (contenido, id_autor, id_ticket) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nota.getContenido());
            ps.setInt(2, obtenerIdUsuario(nota.getAutor().getNombre(), conn));
            ps.setInt(3, idTicket);
            ps.executeUpdate();
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

    public List<Nota> listarPorTicketId(int idTicket) throws SQLException {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM notas WHERE id_ticket = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario autor = usuarioDAO.buscarPorId(rs.getInt("id_autor"));
                Nota nota = new Nota(rs.getString("contenido"), autor);
                lista.add(nota);
            }
        }
        return lista;
    }
}
