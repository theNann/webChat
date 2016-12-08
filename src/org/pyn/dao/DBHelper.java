package org.pyn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pyn on 2016/12/5.
 */
public class DBHelper {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/webChat";
    private static String username = "root";
    private static String password = "mysql";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            try {
                conn = (Connection) DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
