package com.aktivgo.demo;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";

    private static H2Connection h2Connection;
    private static Connection connection;

    private H2Connection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public static @NotNull H2Connection getH2Connection() {
        if (h2Connection == null) {
            h2Connection = (H2Connection) connection;
        }

        return h2Connection;
    }

    public @NotNull Connection getConnection() {
        return connection;
    }
}
