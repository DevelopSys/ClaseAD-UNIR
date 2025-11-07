package controller;

import dao.UsuarioDAOImp;
import model.Usuario;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class PeticionesController {

    // la capa de logica pero no contra base de datos
    // si un usuario se registra -> como actuar ante un error producido por elementos
    // externos
    // mandar un correo electronico a todos los elementos
    // extraer todos los datos de la base de datos
    private UsuarioDAOImp usuarioDAOImp;

    public PeticionesController() {
        usuarioDAOImp = new UsuarioDAOImp();
    }

    public void insertarUsuario(Usuario usuario) {
        // voy intentar insertar un usuario
        // si tengo un error en el correo
        // pido nuevamente el correo y lo intento agregar nuevamente
        Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        String correo;

        do {
            try {
                usuarioDAOImp.insertarDato(usuario);
                fallo = false;
            } catch (SQLException e) {
                System.out.println("Error, correo duplicado");
                System.out.println("Por favor introduce un nuevo correo");
                fallo = true;
                correo = scanner.next();
                usuario.setMail(correo);
            }
        } while (fallo);
        System.out.println("Usuario agregado correctamente");
    }

    public void borrarUsuario(int id) {
        // la logica
        // quiere borrar tambien todos los documentos asociados usuario con id
        int rows = usuarioDAOImp.borrarDatos(id);
        if (rows > 1) {
            System.out.println("Usuarios borrados correctamente");
        } else if (rows == 1) {
            System.out.println("usuario borrado correctamente");
        } else if (rows == 0) {
            System.out.println("No se ha encontrado usuario con ese id");
        } else {
            System.out.println("Fallo en el proceso");
            // dime el id del que quieres borrar
        }
    }

    public void actualiarDatos(String nombre) {
        // DAO -> busqueda por nombre
        // pidiendo de uno en uno el nuevo correo
        // DAO de uno en uno -> actualizar por nombre
    }

    public void listarUsuarios(){
        // System.out.println("Quieres listar todos los datos o solo alguno");
        for (Usuario item: usuarioDAOImp.obtenerListaDatos()) {
            item.mostrarDatos();
        }
    }

}
