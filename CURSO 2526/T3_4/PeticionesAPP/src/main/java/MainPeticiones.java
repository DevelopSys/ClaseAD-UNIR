import controller.PeticionesController;
import model.Usuario;

import java.util.Scanner;

public class MainPeticiones {

    public static void main(String[] args) {
        // VIEW -> la interaccion con el usuario
        PeticionesController peticionesController = new PeticionesController();
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Sistema de gestion de usuarios ");
            System.out.println("1. Insertar usuario");
            System.out.println("2. borrar usuario");
            System.out.println("3. listar usuarios");
            System.out.println("4. exportar usuarios a XML");
            System.out.println("5. exportar usuarios a JSON");
            System.out.println("6. importar usuarios desde JSON");
            System.out.println("7. importar usuarios desde XML");
            System.out.println("8. Salir");
            System.out.println("Indica que quieres hacer");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> {
                    System.out.println("Indica nombre");
                    String nombre = scanner.next();
                    System.out.println("Indica correo");
                    String correo = scanner.next();
                    System.out.println("Indica telefono");
                    int telefono = scanner.nextInt();
                    System.out.println("Indica perfil");
                    int perfil = scanner.nextInt();
                    peticionesController.insertarUsuario(new Usuario(nombre, correo, telefono, perfil));
                }
                case 2 -> {
                    System.out.println("Indica el id del usuario que quiere borrar");
                    int id = scanner.nextInt();
                    peticionesController.borrarUsuario(id);
                }
                case 3 -> {
                    peticionesController.listarUsuarios();
                }
                case 4 -> {
                    peticionesController.exportacionXML();
                }
                case 5 -> {
                    peticionesController.exportarJSON();
                }
                case 6 -> {
                    peticionesController.importarJSON();
                }
                case 7 -> {
                    peticionesController.importarXML();
                }
            }
        } while (opcion != 8);


        peticionesController.insertarUsuario(new Usuario("BorjaM", "borjam@gmail.com", 123, 1));
    }
}
