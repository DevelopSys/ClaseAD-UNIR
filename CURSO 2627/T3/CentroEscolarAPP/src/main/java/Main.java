import database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection(); // la primera vez -> se instancia el obj
        Connection connection1 = DBConnection.getConnection(); // la segunda vez -> se retorna el obj
        try {
            System.out.println(connection.getCatalog());
            System.out.println(connection1.getCatalog());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
