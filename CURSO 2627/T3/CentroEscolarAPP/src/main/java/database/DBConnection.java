package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            createConnection();
        }
        return connection;
    }
    private static void createConnection() {
        try {
            String pass = System.getenv("DB_PASS");
            String user = System.getenv("DB_USER");
            String url = System.getenv("DB_URL");
            connection = DriverManager.getConnection(url, user, pass);

            // connection = DriverManager.getConnection("", new Properties())
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            System.out.println(e.getMessage());
        }
    }


}
