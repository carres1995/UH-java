package org.moodle.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TASK 1: Configuración de Base de Datos Mejorada.
 */
public class ConfigDB {
    private static final String URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://java-proyect-mi-proyecto-mysql-java.h.aivencloud.com:24040/defaultdb?useSSL=true&trustServerCertificate=true";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "avnadmin";
    private static final String PASSWORD = System.getenv("DB_PASSWORD"); 

    private static Connection objConnection = null;

    // Bloque estático para asegurar que el driver se cargue solo una vez
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Critical Error: MySQL Driver not found.");
        }
    }

    public static Connection openConnection() {
        try {
            if (objConnection == null || objConnection.isClosed()) {
                objConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection() {
        try {
            if (objConnection != null && !objConnection.isClosed()) {
                objConnection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
