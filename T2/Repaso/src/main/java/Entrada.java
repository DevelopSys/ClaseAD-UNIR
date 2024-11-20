import dao.UsuarioDAO;
import database.DataBaseConnection;
import model.Usuario;

import java.sql.SQLException;

public class Entrada {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // LOGICA DE LA APP
        // MENU
        // PANTALLAS
        // ACTIVITYS
        // FORM

        // pedir datos y hacer la logica -> UNIQUE
        try {
            usuarioDAO.insertUser(new Usuario("Borja","Martin","correo@gmail.com","1234"));
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion. Es posible que el correo ya exista, quieres volver a probar");
        }
    }
}
