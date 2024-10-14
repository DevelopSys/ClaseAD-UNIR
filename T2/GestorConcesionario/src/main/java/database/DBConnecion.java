package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecion {

    private static Connection connection;


    public Connection getConnection(){
    // si alguien pide una conexion,

        // si no esta -> la creo
        if (connection==null){
            // creo
            newConnection();
        }
        // si esta -> se la doy
        return connection;
    }

    private void newConnection() {
        // uri de conexion jdbc:mysql://localhost:3306/concesionario
        String url = "jdbc:mysql://127.0.0.1:3306/"+SchemaDB.DB_NAME;
        try {
            connection = DriverManager.getConnection(url,"root","");
        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
        }
        System.out.println("Conexion creada correctamente");
    }
    public void closeConnecion(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexion");
        } finally {
            connection = null;
        }
    }

}
