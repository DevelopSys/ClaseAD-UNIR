package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    // new DataBaseConnection() // connection
    // new DataBaseConnection() // connection`
    // new DataBaseConnection() // connection`
    // new DataBaseConnection() // connection`
    // new DataBaseConnection() // connection`
    // new DataBaseConnection() // connection`
    // new DataBaseConnection() // connection`

    private static Connection connection;

    // getter // setter

    public void createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", DBScheme.HOST, DBScheme.PORT, DBScheme.DATABASE);
        //JDBC -> jdbc:mysql://localhost/database
        connection = DriverManager.getConnection(url, "root", "");
    }


    public Connection getConnection() {
        // connection null
        if (connection == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
