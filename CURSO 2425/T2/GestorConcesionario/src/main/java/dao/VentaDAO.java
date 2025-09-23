package dao;

import database.DBConnecion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public VentaDAO() {
        connection = new DBConnecion().getConnection();
    }

    public void realizarVenta(int idEmpleado, int idCoche) throws SQLException {
        String query = "INSERT INTO ventas (id_empleado, id_coche) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,idEmpleado);
        preparedStatement.setInt(2,idCoche);
        preparedStatement.executeUpdate();
    }

}

