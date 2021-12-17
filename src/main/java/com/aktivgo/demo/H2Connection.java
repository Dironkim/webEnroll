package com.aktivgo.demo;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class H2Connection {
    /*private static final String JDBC_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER_CLASS = "org.h2.Driver";*/

    private static Connection connection;

    @Autowired
    public H2Connection(@Value("${database.jdbc_url}") String JDBC_URL, @Value("${database.user}") String USER, @Value("${database.password}") String PASSWORD, @Value("${database.driver_class}") String DRIVER_CLASS) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public @NotNull Connection getConnection() {
        return connection;
    }
}
