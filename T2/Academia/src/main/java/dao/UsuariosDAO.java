package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;
import model.Usuario;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UsuariosDAO {


    MongoCollection coleccionUsuarios;

    public UsuariosDAO() {
        coleccionUsuarios = new MongoDBConnection().getUserCollecion();
    }

    public void insertarUsuario(Usuario usuario) {

        Document document = new Document().append("nombre", usuario.getNombre())
                .append("apellido", usuario.getApellido())
                .append("correo", usuario.getCorreo())
                .append("edad", usuario.getEdad());
        coleccionUsuarios.insertOne(document);
    }

    public void insertarUsuarioPOJO(Usuario usuario) {
        MongoCollection collection = new MongoDBConnection().getCursosCollection();
        collection.insertOne(usuario);
    }

    public void getUsuarios(String mail) {
        Document busqueda = new Document().append("correo", mail);
        // collection.find(documento) -> cursor -> while
        FindIterable iterable = coleccionUsuarios.find(busqueda);
        MongoCursor<Document> cursor = iterable.cursor();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String nombre = document.getString("nombre");
            int edad = document.getInteger("edad");
            // cada una de las caracteristicas
            Usuario usuario = new Usuario();
        }

    }

    public void getElementosPOJO() {
        MongoCollection collection = new MongoDBConnection().getUserCollecion();
        FindIterable<Usuario> iterable1 = collection.find(Usuario.class);
        MongoCursor<Usuario> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Usuario usuario = cursor.next();
            usuario.mostrarDatos();
        }

        // FindIterable<Document> iterable2 = collection.find();
    }

    public void actualizarUsuarioPOJO(int edad) {
        // documento filtro ->
        // documento actualizar -> actualizar
    }

    public void eliminarUsuarioPOJO(String nombre, int edad) {
        // documento filtro ->
        Bson filtrado =
                Filters.and(Filters.eq("nombre", nombre),
                        Filters.gt("edad", edad), Filters.lt("edad", 90));
        // Borja y 60 - 90
        MongoCollection collecion = (MongoCollection) new MongoDBConnection().getUserCollecion();
        collecion.deleteMany(filtrado);
    }
}

/*

UsuarioA
Usuario(nombre,apellido, edad, correo)
UsuarioE(nombre,apellido, edad, correo, pagas)
UsuarioJ(nombre,apellido, edad, correo, pagas, salarioBase - int)
UsuarioA(nombre,apellido, edad, correo, pagas, acciones - double)

 */
