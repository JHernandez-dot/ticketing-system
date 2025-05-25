package com.ticketing.dao;

import com.ticketing.db.ConexionDB;
import com.ticketing.model.Departamento;
import com.ticketing.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public void insertar(Departamento departamento) throws SQLException {
        String sql = "INSERT INTO departamentos (nombre) VALUES (?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, departamento.getNombre());
            ps.executeUpdate();
        }
    }

    public Departamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM departamentos WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Departamento d = new Departamento(rs.getString("nombre"));
                // Si necesitas cargar usuarios, usa UsuarioDAO
                return d;
            }
        }
        return null;
    }

    public List<Departamento> listarTodos() throws SQLException {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Departamento d = new Departamento(rs.getString("nombre"));
                lista.add(d);
            }
        }
        return lista;
    }
}
