package com.ticketing.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class ConexionDB {
    private static Connection conexion;

    public static Connection conectar() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("db.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.user");
                String password = prop.getProperty("db.password");
                conexion = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }
}
