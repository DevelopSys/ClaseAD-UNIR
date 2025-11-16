package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

        Properties properties = new Properties();
        FileInputStream fileInputStream = null;

        try {

            /*fileInputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(fileInputStream);
            String user = "root";
            String pass = "root";
             */
            fileInputStream = new FileInputStream("src/main/resources/database.properties");
            properties.load(fileInputStream);
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String pass = properties.getProperty("pass");
            connection = DriverManager.getConnection(url,user,pass);
            // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/9.3.0
        } catch (FileNotFoundException e) {
            System.out.println("El fichero indicado no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de acceso al fichero");
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion SQL");
        }

    }
}
