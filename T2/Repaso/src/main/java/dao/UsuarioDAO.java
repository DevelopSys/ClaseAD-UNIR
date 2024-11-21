package dao;

import database.DBScheme;
import database.DataBaseConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    // Tadas las transacciones contra BD -> CRUD
    // Tantos metodos como operaciones pueda hacer el usario contra la BD
    // Tantos metodos como operaciones necesite el sistema con respecto a los usuarios

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    // CREATE -> insert
    // UPDATE -> update   // Statement o -PrepareStatement- //  execute (true o false)
    // DELETE -> delete                                         executeUpdate (int)

    // SELECT -> select   // Statement o -PrepareStatement- //  executeQuery (ResultSet)

    public boolean insertUser(Usuario usuario) throws SQLException {

        connection = new DataBaseConnection().getConnection();

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                DBScheme.TAB_USER, DBScheme.COL_NAME, DBScheme.COL_SURNAME, DBScheme.COL_MAIL, DBScheme.COL_PASS);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, usuario.getApellido());
        preparedStatement.setString(3, usuario.getCorreo());
        preparedStatement.setString(4, usuario.getPass());

        return preparedStatement.execute();
        // falso no hay fallo
        // true hay fallo

    }

    public int deleteUser(String correo) throws SQLException {

        connection = new DataBaseConnection().getConnection();

        String query = String.format("DELETE FROM %s WHERE %s=?",
                "usuarios", "correo");

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, correo);
        return preparedStatement.executeUpdate();
        // numero de filas borradas
    }

    public ArrayList<Usuario> getAllUsers() throws SQLException {
        connection = new DataBaseConnection().getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM usuarios");
        // preparedStatement.setString(1,"asdasd")
        resultSet = preparedStatement.executeQuery();
        // [1]x
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (resultSet.next()) {
            String nombre = resultSet.getString(DBScheme.COL_NAME);
            String pass = resultSet.getString(resultSet.getInt(DBScheme.COL_PASS));
            String correo = resultSet.getString(resultSet.getInt(DBScheme.COL_MAIL));
            listaUsuarios.add(new Usuario(nombre, correo, pass));
        }
        return listaUsuarios;
    }

    // LOGIN -> SELECT id FROM usuarios WHERE correo = 'borja@gmail.com' AND password = '1234'
    // LOGIN OK -> 1
    // LOGIN NO OK -> 0


    public boolean getLogin(String correo, String pass) throws SQLException {
        connection = new DataBaseConnection().getConnection();
        // cifrar el pass
        // SELECT id FROM usuarios WHERE correo=el_param_correo AND password = el_param_pass
        preparedStatement = connection.prepareStatement(String.format("SELECT %s FROM %s WHERE %s=? AND %s=?",
                DBScheme.COL_ID, DBScheme.TAB_USER, DBScheme.COL_MAIL, DBScheme.COL_PASS));
        preparedStatement.setString(1,correo);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();
        // [
        // ]
        // *
        // .next() indica si hay registra
        return resultSet.next();
    }
}
