package dao;

import database.DBConnecion;
import database.SchemaDB;
import model.Empleado;
import model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmpleadoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    // statement
    private ResultSet resultSet;

    // constructor por defecto
    public EmpleadoDAO() {
        connection = new DBConnecion().getConnection();
    }

    public void insertarEmpleado(Empleado empleado) throws SQLException {

        preparedStatement = connection.prepareStatement(
                String.format("INSER INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                        SchemaDB.TAB_EMP,
                        SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_MAIL, SchemaDB.TAB_KIN));
        preparedStatement.setString(1, empleado.getNombre());
        preparedStatement.setString(2, empleado.getApellido());
        preparedStatement.setInt(3, empleado.getTelefono());
        preparedStatement.setString(4, empleado.getCorreo());
        preparedStatement.setInt(5, empleado.getTipo().getId());
        preparedStatement.execute();
    }

    public void obtenerEmpleadoMes(int numero) throws SQLException {
        String query = "SELECT * FROM %s ORDER BY %s DESC LIMIT ?";
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.COL_EMP_SALE));
        preparedStatement.setInt(1, numero);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
            String apellido = resultSet.getString(SchemaDB.COL_EMP_SURNAME);
            Empleado empleado = new Empleado(nombre,apellido);
            empleado.mostrarDatos();
        }
    }

    public void realizarVenta(int id) throws SQLException {
        String query = "UPDATE %s SET %s = %s+1 WHERE %s = ?";
        preparedStatement = connection.prepareStatement(String.format(query,SchemaDB.TAB_EMP,
                SchemaDB.COL_EMP_SALE,SchemaDB.COL_EMP_SALE, SchemaDB.COL_ID));
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }

    public Empleado getEmpleado(int id) throws SQLException {

        preparedStatement = connection.prepareStatement(String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_EMP, SchemaDB.COL_ID));
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
            String apellido = resultSet.getString(SchemaDB.COL_EMP_SURNAME);
            String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
            int telefpno = resultSet.getInt(SchemaDB.COL_EMP_PHO);
            int tipo = resultSet.getInt(SchemaDB.COL_EMP_KIN);
            return getEmpleado(nombre, apellido, correo, telefpno, tipo);
        }

        return null;
    }

    public Empleado getEmpleado(String nombre, String apellido, String correo, int telefono, int tipo) {
        Tipo tipo1 = null;
        switch (tipo) {
            case 1:
                tipo1 = Tipo.EXTERNO;
                break;
            case 2:
                tipo1 = Tipo.INDEFINIDO;
                break;
            case 3:
                tipo1 = Tipo.BECARIO;
                break;
        }
        return new Empleado(nombre, apellido, correo, telefono, tipo1);
    }
}
