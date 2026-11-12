//Lloyd Musau, BBIT, 227326, 7th Nov 2026

package com.musau.sufeeds;

// these are needed to connect java to mysql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    // database details
    private static String URL = "jdbc:mysql://localhost:3306/db_lloydmusau_227326?useSSL=false&allowPublicKeyRetrieval=true";
    private static String USER = "root";
    private static String PASSWORD = "lloyd44";

    // this method connects to the database
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con;
    }
}