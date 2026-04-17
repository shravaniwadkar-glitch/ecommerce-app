package com.ecommerce;
import java.sql.*;

public class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    static final String USER = "root";
    static final String PASSWORD = "root1234";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}