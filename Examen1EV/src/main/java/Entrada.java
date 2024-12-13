import dao.UsuarioDAO;
import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        System.out.println("Bienvenido al programa de gestion de usuarios");
        int opcion;
        do {
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Credenciales");
            System.out.println("4. Exportar");
            System.out.println("5. Salir");
            System.out.println("Introduce la opcion a desarrollar");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    try {
                        usuarioDAO.registrarUsuario(new Usuario("Borja", "Martin", "correo@gmail.com", "1234"));
                    } catch (SQLException e) {
                        System.out.println("Error, correo duplicado");
                    }
                    break;
                case 2:
                    try {
                        usuarioDAO.listarUsuarios(1); // arraylist
                    } catch (SQLException e) {
                        System.out.println("Error en el listado, quieres hacer otra cosa");
                    }
                    break;
                case 3:
                    try {
                        if (usuarioDAO.comprobarUsuario("borja@gmail.com","1234")){
                            System.out.println("Credenciales correctas");
                        } else {
                            System.out.println("Credenciales incorrectas");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al comprobar, quieres volver a probar");
                    }
                    break;
                case 4:
                    try {
                        usuarioDAO.exportarUsuarios();
                    } catch (IOException e) {
                        System.out.println("Error al escribir el fichero");
                    } catch (SQLException e) {
                        System.out.println("Error al tratar con la BD");
                    }
                    break;
                case 5:
                    System.out.println("Saliedo");
                    break;
            }


        } while (opcion != 5);


    }
}
