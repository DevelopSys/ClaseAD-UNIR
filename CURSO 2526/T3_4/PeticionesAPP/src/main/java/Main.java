import database.DBConnection;
import database.SchemeDB;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        // CREATE
        // INSERT INTO (nombre, mail, telefono,id_perfil)

        /*
        try {
            Statement statement = connection.createStatement();
            String nombre = "Nombre Insertar";
            String mail = "Mail Insertar 2";
            int telefono = 123;
            int idPerfil = 1;

            //String query = "INSERT INTO usuarios (nombre,mail,telefono,id_perfil) " +
            //         "VALUES ("+nombre+","+mail+",'"+telefono+"','"+idPerfil+"')";

            String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s',%d,%d)",
                    SchemeDB.TAB_NAME,
                    SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE,
                    nombre, mail, telefono, idPerfil);
            boolean fallo = statement.execute(query);
            System.out.println("El resultado de la query es " + fallo);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error, clave duplicada");
            // pide otro mail
        } catch (SQLException e) {
            System.out.println("Error de ejecucion de query");
            System.out.println(e.getMessage());
        }

         */
        // ACTUALIZACION - BORRADO
        // UPDATE usuarios SET id_perfil=3 WHERE telefono=123
        /*
        String query = String.format("UPDATE %s SET %s=? WHERE %s=? ", SchemeDB.TAB_NAME,
                SchemeDB.COL_PROFILE,
                SchemeDB.COL_PHONE);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,3);
            preparedStatement.setInt(2,234);
            int numRow = preparedStatement.executeUpdate();
            if (numRow>0){
                System.out.println("Has actualizado los registros de la base de datos");
                System.out.println("En concreto se han visto afectadas "+numRow+" filas");
            } else {
                System.out.println("Ninguna fila se ha cambiado");
            }

        } catch (SQLException e) {
            System.out.println("Error en la sentencia query");
        }*/

        String query = String.format("SELECT * FROM %s",SchemeDB.TAB_NAME);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //                x
            //   r1,r2,r3,r4,r5
            while (resultSet.next()) {
                // sacas los datos
                String nombre = resultSet.getString(SchemeDB.COL_NAME);
                int telefono = resultSet.getInt(SchemeDB.COL_PHONE);
                System.out.printf("Nombre %s Telefono %d%n", nombre, telefono);
            }
            System.out.println("Datos obtenidos correctamento");

            // LOGIN -> SELECT id FROM USUARIOS WHERE USER = 'asdasd' AND pass = 'asasdasd'
            // ResultSet rs = prepareStatement.executeQuery();
            // if (rs.next()){
                //  hay login
            // } else {
                //  No login
            //}

        } catch (SQLException e) {
            System.out.println("Error en SQL");
        }
    }
}


