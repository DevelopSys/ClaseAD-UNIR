package controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dao.UsuarioDAOImp;

import model.Usuario;
import model.UsuarioJSON;
import model.UsuarioResponse;
import model.UsuarioXML;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.desktop.UserSessionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public void listarUsuarios() {
        // System.out.println("Quieres listar todos los datos o solo alguno");
        for (Usuario item : usuarioDAOImp.obtenerListaDatos()) {
            item.mostrarDatos();
        }
    }

    public void importarJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://dummyjson.com/users");
            UsuarioResponse response = mapper.readValue(url, UsuarioResponse.class);
            for (UsuarioJSON item : response.getUsers()) {
                /*
                int telefono = Integer.parseInt(item.getPhone().replaceAll(" ", "")
                        .replaceAll("-", "")
                        .replaceAll("\\+", ""));
                 */
                Usuario usuario = new Usuario(item.getFirstName(), item.getEmail(), item.getAge(), 2);
                usuarioDAOImp.insertarDato(usuario);
            }

        } catch (MalformedURLException e) {
            System.out.println("URL mal creada, servidor erroneo");
        } catch (StreamReadException | SQLException | DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarJSON() {
        // ObjectMapper mapper = new ObjectMapper();
        // ObjectWriter response = mapper.writerFor(UsuarioResponse.class);
        ArrayList<Usuario> lista = usuarioDAOImp.obtenerListaDatos();
        JSONArray arrayJson = new JSONArray();
        JSONObject jsonObject = null;
        for (Usuario item : lista) {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", item.getNombre());
            jsonObject.put("correo", item.getMail());
            jsonObject.put("telefono", item.getTelefono());
            arrayJson.put(jsonObject);
        }
        File file = new File("src/main/java/controller/usuarios.json");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(file));
            pw.println(arrayJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pw.close();
        }
        // System.out.println(arrayJson.toString());


        //Usuario usuario = lista.getFirst();
        //File file = new File("src/main/java/controller/usuarios.json");
        //PrintWriter pw = null;
        /*try {
            pw = new PrintWriter(new FileWriter(file));
            pw.println(usuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            pw.close();
        }*/
        /*
        try {
            response.createGenerator(new File("src/main/java/controller/usuario.json"),
                    JsonEncoding.UTF8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void exportacionXML() {
        JAXBContext context = null;
        Marshaller marshaller;
        List<Usuario> lista = usuarioDAOImp.obtenerListaDatos();
        UsuarioXML usuarioXML = new UsuarioXML();
        usuarioXML.setLista(lista);
        try {
            context = JAXBContext.newInstance(UsuarioXML.class);
            marshaller = context.createMarshaller();
            marshaller.marshal(usuarioXML, new File("src/main/java/controller/usuarios.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    public void importarXML() {
        JAXBContext context;
        Unmarshaller unmarshaller;

        try {
            context = JAXBContext.newInstance(UsuarioXML.class);
            unmarshaller = context.createUnmarshaller();
            UsuarioXML usuarioXML = (UsuarioXML) unmarshaller.unmarshal(new File("src/main/java/controller/usuarios.xml"));
            for (Usuario item : usuarioXML.getLista()) {
                usuarioDAOImp.insertarDato(item);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
