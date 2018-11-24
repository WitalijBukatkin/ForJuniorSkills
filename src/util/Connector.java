package util;

import java.sql.*;

public class Connector {
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(connection==null) {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ForJuniorSkills", "root2", "qwerty");
        }
        return connection;
    }

    public static Statement getStatement() throws Exception{
        return getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }
}