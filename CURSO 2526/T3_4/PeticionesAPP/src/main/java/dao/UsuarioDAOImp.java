package dao;

import database.DBConnection;
import database.SchemeDB;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAOImp implements InterfazDAO<Usuario>, UsuarioDAO {

    // ir contra base de datos
    private Connection connection;
    // statement -> statement.executeUpdate("INSERT INTO asdasdasdasd")
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UsuarioDAOImp() {
        connection = DBConnection.getConnection();
    }


    @Override
    public boolean insertarDato(Usuario data) throws SQLException {
        System.out.println("Importando datos "+data.getId());
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE
        );


        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, data.getNombre());
        preparedStatement.setString(2, data.getMail());
        preparedStatement.setInt(3, data.getTelefono());
        preparedStatement.setInt(4, data.getIdPerfil());
        return preparedStatement.execute();


    }

    @Override
    public ArrayList<Usuario> obtenerListaDatos() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s", SchemeDB.TAB_NAME));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int  id = resultSet.getInt(SchemeDB.COL_ID);
                String nombre = resultSet.getString(SchemeDB.COL_NAME);
                String mail = resultSet.getString(SchemeDB.COL_MAIL);
                int telefono = resultSet.getInt(SchemeDB.COL_PHONE);
                int idPerfil = resultSet.getInt(SchemeDB.COL_PROFILE);
                listaUsuarios.add(new Usuario(id,nombre, mail, telefono, idPerfil));
            }
            return listaUsuarios;
        } catch (SQLException e) {
            // error en la query
        }
        return listaUsuarios;
    }



    @Override
    public void actualizarDato(Usuario datoNuevo) {

    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?",SchemeDB.TAB_NAME,SchemeDB.COL_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion de la query");
        }
        return -1;
    }

    @Override
    public ArrayList<String> obtenerCorreos() {
        return null;
    }

    @Override
    public ArrayList<Usuario> obtenerPerfil(int idPerfil) {
        return null;
    }
}
