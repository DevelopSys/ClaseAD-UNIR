package database;

public interface SchemaDB {

    // 1. conectar dos clases que no tienen nada que ver
    // con los metodos abs que tiene dicha interfaz

    // 2. Almacen de constantes variables -> finales (no de metodos)
    String DB_NAME = "concesionario";
    String TAB_EMP = "empleados";
    String COL_ID = "id";
    String COL_EMP_NAME = "nombre";
    String COL_EMP_SURNAME = "apellido";
    String COL_EMP_MAIL = "correo";
    String COL_EMP_PHO = "telefono";

}
