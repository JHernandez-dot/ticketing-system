package com.ticketing.dao;

import com.ticketing.db.ConexionDB;
import com.ticketing.model.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    public void insertar(Rol rol) throws SQLException {
        String sql = "INSERT INTO roles (nombre) VALUES (?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rol.getNombre());
            ps.executeUpdate();
        }
    }

    public Rol buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM roles WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Rol(rs.getString("nombre"));
            }
        }
        return null;
    }

    public List<Rol> listarTodos() throws SQLException {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM roles";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Rol r = new Rol(rs.getString("nombre"));
                lista.add(r);
            }
        }
        return lista;
    }
}
