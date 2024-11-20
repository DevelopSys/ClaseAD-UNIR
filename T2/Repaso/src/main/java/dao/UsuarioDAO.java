package dao;

import database.DataBaseConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    // Tadas las transacciones contra BD -> CRUD
    // Tantos metodos como operaciones pueda hacer el usario contra la BD
    // Tantos metodos como operaciones necesite el sistema con respecto a los usuarios

    private Connection connection;
    private PreparedStatement preparedStatement;
    // CREATE -> insert
    // UPDATE -> update   // Statement o -PrepareStatement- //  execute (true o false)
    // DELETE -> delete                                         executeUpdate (int)

    // SELECT -> select   // Statement o -PrepareStatement- //  executeQuery (ResultSet)

    public boolean insertUser(Usuario usuario) throws SQLException {

        connection = new DataBaseConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                "usuarios","nombre","apellido","correo","pass");

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,usuario.getNombre());
        preparedStatement.setString(2,usuario.getApellido());
        preparedStatement.setString(3,usuario.getCorreo());
        preparedStatement.setString(4,usuario.getPass());



        return false;
    }
}
