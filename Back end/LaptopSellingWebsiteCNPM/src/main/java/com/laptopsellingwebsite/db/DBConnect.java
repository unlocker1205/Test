package com.laptopsellingwebsite.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnect {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/laptopsellingwebsitecnpm?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static DBConnect dbConnect;
    private static Connection connection;

    private DBConnect() {
    }

    public static DBConnect getInstance() {
        if (dbConnect == null) {
            dbConnect = new DBConnect();
        }
        return dbConnect;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        if ((connection == null) || connection.isClosed())
            Class.forName("com.mysql.cj.jdbc.Driver");
         connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private boolean isConnect() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    public PreparedStatement get(String sql) throws SQLException, ClassNotFoundException {
        try {
            if (!isConnect())
                connect();
            return connection.prepareStatement(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
