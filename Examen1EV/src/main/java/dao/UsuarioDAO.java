package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.SequencedCollection;

public class UsuarioDAO {

    private Connection connection;
    private PreparedStatement preparedStatement; // INSERT UPDATE DETELE
    private ResultSet resultSet; // SELECT

    // registrar
    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        // pedido los datos aqui -> NO!!!!
        String query =  String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                SchemaDB.TABUSER,
                SchemaDB.COLNAME, SchemaDB.COLSURNAME, SchemaDB.COLMAIL, SchemaDB.COLPASS
                );
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,usuario.getNombre());
        preparedStatement.setString(2,usuario.getApellido());
        preparedStatement.setString(3,usuario.getCorreo());
        preparedStatement.setString(4,usuario.getPass());
        boolean fallo = preparedStatement.execute();
        new DBConnection().closeConnection();
        return fallo;

    }
    // listar
    public ArrayList<Usuario> listarUsuarios(int modo) throws SQLException {
        String query = String.format("SELECT * FROM %s", SchemaDB.TABUSER);
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (resultSet.next()){
            String nombre = resultSet.getString(SchemaDB.COLNAME);
            String apellido = resultSet.getString(SchemaDB.COLSURNAME);;
            String mail = resultSet.getString(SchemaDB.COLMAIL);
            String pass = resultSet.getString(SchemaDB.COLPASS);
            Usuario usuario = new Usuario(nombre,apellido,mail,pass);
            listaUsuarios.add(usuario);
            if (modo==1){
                System.out.println(usuario);
            }
        }
        new DBConnection().closeConnection();

        return listaUsuarios;

    }

    // comprobar
    public boolean comprobarUsuario(String correo, String pass) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s=? AND %s=?",
                SchemaDB.TABID,
                SchemaDB.TABUSER,
                SchemaDB.COLMAIL,
                SchemaDB.COLPASS);
        connection = new DBConnection().getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,correo);
        preparedStatement.setString(2,pass);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();

    }
    // exportar
    public void exportarUsuarios() throws IOException, SQLException {
        //  ObjectOutpurStreal -> FileOutpuStream -> File
        File file = new File("src/main/java/resources/usuarios.obj");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(listarUsuarios(-1));
        objectOutputStream.close();
    }
}
