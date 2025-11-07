package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public interface InterfazDAO<T> {

    // Usuarios

    // todos los metodos que el usuario puede hacer contra BD
    // "TODOS" LOS METODOS DE LA INTERFAZ SON ABS SIN CUERPO
    // CRUD GENERICO
    boolean insertarDato(T data) throws SQLException;
    ArrayList<T> obtenerListaDatos();
    void actualizarDato(T datoNuevo);
    int borrarDatos(int id);


}
