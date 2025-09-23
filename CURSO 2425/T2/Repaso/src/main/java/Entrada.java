import dao.UsuarioDAO;
import database.DataBaseConnection;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        // LOGICA DE LA APP
        // MENU
        // PANTALLAS
        // ACTIVITYS
        // FORM

        // pedir datos y hacer la logica -> UNIQUE
        /*try {
            if (!usuarioDAO.insertUser(new Usuario("Borja","Martin","borja@correo.com","1234"))){
                System.out.println("Usuario insertado con exito");
            } else {
                System.out.println("Fallo en el proceso de insercion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en la ejecucion. Es posible que el correo ya exista, quieres volver a probar");
        }*/

        /*try {
            // 0
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indica el correo que quieres borrar");
            String correo = scanner.next();
            int deleteRows = usuarioDAO.deleteUser(correo);
            System.out.println("El numero de registros borrados es de "+deleteRows);
        } catch (SQLException e) {
            System.out.println("error en la ejecucion");
        }*/

        /*try {
            for ( Usuario item : usuarioDAO.getAllUsers() ) {
                // JAVAMAIL
                item.getCorreo();
            }
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion");
        }*/

        boolean login = false;
        boolean bloqueo = false;
        int intentos = 4;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indicame el correo");
            String correo = scanner.next();
            System.out.println("Indicame las pass");
            String pass = scanner.next();
            try {
                login = usuarioDAO.getLogin(correo, pass);
                intentos--;
                if (login) {
                    System.out.println("Login correcto, adelante");
                } else {
                    if (intentos == 0) {
                        bloqueo = true;
                    }
                    System.out.println("Login incorrecto, fallo");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(intentos);
        } while ( intentos !=0 && !login);

        if (intentos == 0 && bloqueo) {
            System.out.println("Caja bloqueda");
        }
    }
}
