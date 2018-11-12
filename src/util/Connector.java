package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(connection==null) {
            Class.forName("com.mysql.jdbc.Driver" );
            Properties properties = new Properties();
            properties.setProperty("user", "root2" );
            properties.setProperty("password", "qwerty");
            properties.setProperty("characterEncoding", "utf8" );
            properties.setProperty("useSSL", "false" );
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ForJuniorSkills", properties);
        }
        return connection;
    }
}