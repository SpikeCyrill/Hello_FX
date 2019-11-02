package sample;

import java.sql.*;
import java.util.Properties;

public class Connector {




    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        //Class.forName("com.mysql.jdbc.Driver");

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "root");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB?serverTimezone=UTC", connectionProperties);
        return conn;
    }

}
