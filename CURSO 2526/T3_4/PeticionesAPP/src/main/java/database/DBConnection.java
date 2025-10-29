package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // LA CONEXION
    private static Connection connection;

    public static Connection getConnection(){
        if (connection==null){
            // inicializala
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://127.0.0.1:41063/peticiones";
        // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/9.3.0
        try {
            connection = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            System.out.println("Error en conexion SQL");
        }
    }
}
