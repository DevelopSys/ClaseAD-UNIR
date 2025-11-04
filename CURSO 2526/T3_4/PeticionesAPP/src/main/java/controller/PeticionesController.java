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

    public PeticionesController(){
        usuarioDAOImp = new UsuarioDAOImp();
    }

    public void insertarUsuario(Usuario usuario){
        // voy intentar insertar un usuario
            // si tengo un error en el correo
                // pido nuevamente el correo y lo intento agregar nuevamente
        boolean fallo = false;
        do{
            try {
                usuarioDAOImp.insertarDato(usuario);
                fallo = false;
                System.out.println("Usuario agregado correctamente");
            } catch (SQLException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("El correo ya esta en la bd");
                System.out.println("Introduce el nuevo correo electronico");
                String correo = scanner.next();
                usuario.setMail(correo);
                fallo = true;
            }
        } while (fallo);


    }

    public void actualiarDatos(String nombre){
        // DAO -> busqueda por nombre
        // pidiendo de uno en uno el nuevo correo
        // DAO de uno en uno -> actualizar por nombre
    }

}
