package controller;

import database.DBConnecion;
import database.SchemaDB;
import model.Empleado;

import java.sql.*;

public class Concesionario {
    // Statament -> "Query directa" -> INSERT INTO empleados (nombre, apellido
    // true o false -> , correo, telefono) VALUES ('Borja', 'Martin','bmartin@correo',1234)
    // CREATE
    // nº filas afectadas -> UPDATE DELETE INSERT INTO empleados (nombre, apellido
    // PrepareStatement -> "Query con template" ->INSERT INTO empleados (nombre, apellido
    // , correo, telefono) VALUES (?,?,?,?)
    // setInt(4,123)
    // setString(1,"Borja")
    // setString(2,"Martin")
    // setString(3,"correo@correo.es")

    // Create Update Delete -> MODIFICAN TABLA
    // Read -> OBTIENE VECTOR DE VALORES
    // insertar trabajador
    public void insertarTrabajador(Empleado empleado) {
        // SchemaDB.nombre
        // Connection -> Statement (query) -> execute
        Connection connection = new DBConnecion().getConnection();
        // ya puedo acceder a la base de datos
        try {
            Statement statement = connection.createStatement();
            String Psquery = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_MAIL, SchemaDB.COL_EMP_PHO, SchemaDB.COL_EMP_KIN);
            PreparedStatement preparedStatement = connection.prepareStatement(Psquery);
            preparedStatement.setString(1,empleado.getNombre());
            preparedStatement.setString(2,empleado.getApellido());
            preparedStatement.setString(3,empleado.getCorreo());
            preparedStatement.setInt(4,empleado.getTelefono());
            preparedStatement.setInt(5,6);
            preparedStatement.executeUpdate();

            /*String query = "INSERT INTO " + SchemaDB.TAB_EMP +
                    " (" + SchemaDB.COL_EMP_NAME + "," + SchemaDB.COL_EMP_SURNAME + "," + SchemaDB.COL_EMP_MAIL + "," + SchemaDB.COL_EMP_PHO + ") " +
                    "VALUES ('" + empleado.getNombre() + "','" + empleado.getApellido() + "','" + empleado.getCorreo() + "'," + empleado.getTelefono() + ")";*/
            // %s ->string
            // %d ->int
            // %f ->double o float
            // %b -> boolean
            // %c -> char
            String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s','%s',%d)",
                    SchemaDB.TAB_EMP,
                    SchemaDB.COL_EMP_NAME, SchemaDB.COL_EMP_SURNAME, SchemaDB.COL_EMP_MAIL, SchemaDB.COL_EMP_PHO,
                    empleado.getNombre(), empleado.getApellido(), empleado.getCorreo(), empleado.getTelefono());
            // statement.execute(query); // hay o no fallo
            // statement.executeUpdate() // cuantas filas estan afectadas

        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos");
        }
    }
    public int borrarUsuario(int id){
        Connection connection = new DBConnecion().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM "+SchemaDB.TAB_EMP+" WHERE id=?");
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en la creacion de la query");
        }

        return 0;
    }

    // lectura
    public void leerUsuarios(int tipo){
        // no se puede mapear de forma directa -> Vector[[nombre, apellido, correo],[nombre, apellido, correo]]
        // Connection -> Statement / PrepareStatement -> executeQuery -> ResultSet
        Connection connection = new DBConnecion().getConnection();
        // SELECT * FROM empleado WHERE ID=7;
        String query = String.format("SELECT * FROM %s WHERE %s=?",SchemaDB.TAB_EMP,SchemaDB.COL_EMP_KIN);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,tipo);
            ResultSet resultSet = preparedStatement.executeQuery();
            //  v
            //  R,R,R,R,R
            while (resultSet.next()){
                String nombre = resultSet.getString(SchemaDB.COL_EMP_NAME);
                String correo = resultSet.getString(SchemaDB.COL_EMP_MAIL);
                int tipo1 = resultSet.getInt(SchemaDB.COL_EMP_KIN);
                System.out.printf("Nombre del empleado %s\n\tCorreo del empleado %s\n\tTipo del empleado %s\n",nombre,correo,tipo1);
            }
        } catch (SQLException e) {
            System.out.println("error en la query");
        }
    }

}
