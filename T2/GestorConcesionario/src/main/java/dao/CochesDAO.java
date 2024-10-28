package dao;

import database.DBConnecion;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Clase destinada a la gestion de los coches contra la BD -> querys
public class CochesDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CochesDAO() {
        connection = new DBConnecion().getConnection();
    }

    // que se pueda añadir un coche a la base de datos
    public void addCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT into %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_CH, SchemaDB.COL_CH_MAR, SchemaDB.COL_CH_MOD, SchemaDB.COL_CH_CV, SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMarca());
        preparedStatement.setString(2, coche.getModelo());
        preparedStatement.setInt(3, coche.getCv());
        preparedStatement.setInt(4, coche.getPrecio());
        preparedStatement.execute();
    }
    public ArrayList<Coche> getModeloCochesMarca(String marcaParam) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s=?", SchemaDB.TAB_CH, SchemaDB.COL_CH_MAR);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,marcaParam);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }
    public ArrayList<Coche> getCochePrecio(int precioParam) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s <= ?",SchemaDB.TAB_CH,SchemaDB.COL_CH_PRE);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,precioParam);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }
    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche> listaResultado = new ArrayList<>();
        while (datosResultantes.next()){
            String marca = resultSet.getString(SchemaDB.COL_CH_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CH_MOD);
            int cv = resultSet.getInt(SchemaDB.COL_CH_CV);
            int precio = resultSet.getInt(SchemaDB.COL_CH_PRE);
            listaResultado.add(mapearCoche(marca,modelo,cv,precio));
        }
        return listaResultado;
    }
    private Coche mapearCoche(String marca, String modelo, int cv, int precio){
        return new Coche(marca,modelo,cv,precio);
    }

    public void realizarVenta(int id){
        // DELETE -> WHERE id= id
        // UPDATE -> estado = false WHERE id= id
    }


}
