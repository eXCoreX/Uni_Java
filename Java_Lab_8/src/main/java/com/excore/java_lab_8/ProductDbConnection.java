package com.excore.java_lab_8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductDbConnection {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String username = System.getenv("PRDBUSR");
            String password = System.getenv("PRDBPWD");
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/productdb", username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
