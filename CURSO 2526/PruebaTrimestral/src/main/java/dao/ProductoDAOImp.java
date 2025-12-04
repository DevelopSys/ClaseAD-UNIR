package dao;

import database.DBConnection;
import model.Producto;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAOImp implements ProductoDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public void addProducto(Producto producto) {
        // como funciona el metodo contra BD
        connection = DBConnection.getConnection();
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PRO,
                SchemaDB.COL_NAME, SchemaDB.COL_PRICE, SchemaDB.COL_CAT
        );
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setDouble(2, producto.getPrecio());
            preparedStatement.setString(3, producto.getCategoria());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    @Override
    public void borrarProducto(int id) {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_PRO, SchemaDB.COL_ID);
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en la transaccion de rollback");
            }
        }

    }

    @Override
    public void actualizarProductos(String categoria) {
        // actualizar el precio de los productos perifericos a incremento del 10%
        // UPDATE FROM productos SET precio=precio*1.1 WHERE categoria = perifericos
        connection = DBConnection.getConnection();
        String query = String.format("UPDATE FROM %s SET %s = precio*1.1 WHERE %s = ?",
                SchemaDB.TAB_PRO,
                SchemaDB.COL_PRICE, SchemaDB.COL_CAT
        );
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoria);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public ArrayList<Producto> getAllProductos() {
        ArrayList<Producto> listado = new ArrayList<>();


        try {
            ;
            preparedStatement = connection.prepareStatement("SELECT * FROM productos");
            resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                String nombre = resultSet.getString(SchemaDB.COL_NAME);
                String categoria = resultSet.getString(SchemaDB.COL_CAT);;
                double precio = resultSet.getDouble(SchemaDB.COL_PRICE);;
                int id = resultSet.getInt(SchemaDB.COL_ID);
                listado.add(new Producto(id,precio,nombre,categoria));
            }
            return listado;
        } catch (SQLException e) {
            System.out.println("Error de SQL");
        }

        return listado;
    }


}
