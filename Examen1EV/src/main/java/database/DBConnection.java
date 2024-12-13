package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // singleton
    private static Connection connection;

    // pedir conexion
    public Connection getConnection(){
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    // crear conexion
    private void createConnection() {
        String URL = String.format("jdbc:mysql://%s%s/%s",SchemaDB.HOST,SchemaDB.PORT, SchemaDB.DBNAME);
        try {
            DriverManager.getConnection(URL, SchemaDB.DBUSER, SchemaDB.DBPASS);
        } catch (SQLException e) {
            System.out.println("Error en la conexion con la base de datos");
        }
    }


    // cerrar conexion
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en el cerrado de conexion");
        } finally {
            connection = null;
        }
    }
}
