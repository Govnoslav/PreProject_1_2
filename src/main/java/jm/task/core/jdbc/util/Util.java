package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final  String URL = "jdbc:mysql://localhost:3306/kata";
    public static final  String USER = "root";
    private static final String PASSWORD = "root";
    static {

    }

    private Util() {

    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
