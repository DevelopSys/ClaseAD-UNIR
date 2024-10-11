import database.DBConnecion;

import java.sql.Connection;

public class Entrada {

    public static void main(String[] args) {

        DBConnecion dbConnecion = new DBConnecion();
        Connection connection = dbConnecion.getConnection();
        // trabajas con la conexion
        dbConnecion.closeConnecion();

        DBConnecion dbConnecion2 = new DBConnecion();
        Connection connection2 = dbConnecion2.getConnection();
        DBConnecion dbConnecion3 = new DBConnecion();
        Connection connection3 = dbConnecion3.getConnection();

    }
}
