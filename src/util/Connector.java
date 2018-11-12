package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(connection==null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ForJuniorSkills", "root2", "qwerty");
        }
        return connection;
    }
}