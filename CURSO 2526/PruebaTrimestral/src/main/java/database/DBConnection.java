package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection(){

        if(connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/prueba_t1","root","root");
        } catch (SQLException e) {
            System.out.println("Error conexion");
        }
    }
}
