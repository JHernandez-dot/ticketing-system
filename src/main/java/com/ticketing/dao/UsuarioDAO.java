package com.ticketing.dao;

import com.ticketing.db.ConexionDB;
import com.ticketing.model.Usuario;
import com.ticketing.model.Rol;
import com.ticketing.model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private RolDAO rolDAO = new RolDAO();
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    public void insertar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, id_rol, id_departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            // Aquí se asume que Rol y Departamento tienen un id, debes agregar ese campo si no lo tienes
            ps.setInt(2, obtenerIdRol(usuario.getRol().getNombre(), conn));
            ps.setInt(3, obtenerIdDepartamento(usuario.getDepartamento().getNombre(), conn));
            ps.executeUpdate();
        }
    }

    private int obtenerIdRol(String nombreRol, Connection conn) throws SQLException {
        String sql = "SELECT id FROM roles WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreRol);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
            else throw new SQLException("Rol no encontrado: " + nombreRol);
        }
    }

    private int obtenerIdDepartamento(String nombreDep, Connection conn) throws SQLException {
        String sql = "SELECT id FROM departamentos WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreDep);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
            else throw new SQLException("Departamento no encontrado: " + nombreDep);
        }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Rol rol = rolDAO.buscarPorId(rs.getInt("id_rol"));
                Departamento dep = departamentoDAO.buscarPorId(rs.getInt("id_departamento"));
                return new Usuario(rs.getString("nombre"), rol, dep);
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Rol rol = rolDAO.buscarPorId(rs.getInt("id_rol"));
                Departamento dep = departamentoDAO.buscarPorId(rs.getInt("id_departamento"));
                Usuario u = new Usuario(rs.getString("nombre"), rol, dep);
                lista.add(u);
            }
        }
        return lista;
    }
}
