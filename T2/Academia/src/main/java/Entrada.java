import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import database.DBScheme;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entrada {

    public static void main(String[] args) {
        // Connection
        String connectionString = "mongodb+srv://%s:%s@cluster0.cg3in.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(String.format(connectionString, DBScheme.USER, DBScheme.PASS)))
                .serverApi(serverApi)
                .build();

        //MongoClient mongoClient = MongoClients.create(settings);
        MongoClient mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));

        // database
        MongoDatabase database = mongoClient.getDatabase("academia");
        // collection
        MongoCollection collection = database.getCollection("usuarios");
        // Create -> insertOne / many
        // DOCUMENT -> {par: valor}



        /*Document documentInsercion = new Document();
        documentInsercion.append("nombre","Borja");
        //{nombre:"borja"}
        documentInsercion.append("apellido","Martin");
        {nombre:"borja",apellido:"Martin"}
        documentInsercion.append("edad",40);
        {nombre:"borja",apellido:"Martin", edad: 40}
        documentInsercion.append("correo","borja@correo.com");
        {nombre:"borja",apellido:"Martin", edad: 40, correo: "correo@gmail"}
        collection.insertOne(documentInsercion);*/
        // db.coleccion.insertOne({"nombre":"Borja"})
        List<Document> listaInsercion = Arrays.asList(
                new Document().append("nombre", "Juan").append("apellido", "Gomez").append("edad", 20).append("correo", "correo@juan.es"),
                new Document().append("nombre", "Maria").append("apellido", "Perez").append("edad", 70).append("correo", "correo@maria.es"),
                new Document().append("nombre", "Celia").append("apellido", "Ruiz").append("edad", 50).append("correo", "correo@celia.es")
        );

        // db.coleccion.insertMany([{nombre:"Borja",apelldo:"Martin"},{nombre:"Borja",apelldo:"Martin"},{nombre:"Borja",apelldo:"Martin"}])
        // collection.insertMany(listaInsercion);


        // Update -> update
        // Documento de filtro: que documento quieres actualizar -> condiciones de actualizacion
        // db.collecion.updateOne({nombre: "Borja",edad:{$lte:40}},{$set:{edad: 50}})
        // Document documentBusqueda = new Document().append("edad", new Document().append("$lte",55));
        // Document documentoCambio = new Document().append("$set",new Document("edad",30));
        // UpdateResult result = collection.updateMany(documentBusqueda,documentoCambio);
        // System.out.printf("El resultado de la actualizacion han sido %d registros",result.getModifiedCount());

        // Delete -> delete
        /*Document document = new Document();
        document.append("edad",new Document().append("$lt",70));
        DeleteResult deleteResult = collection.deleteMany(new Document().append("edad",new Document().append("$lt",70)));
        System.out.println("El resultado del borrado es: "+deleteResult.getDeletedCount());*/

        // Select -> find
        // db.coleccion.find() -> todos l
        // db.coleccion.find({nombre: "Borja"}) ->
        // db.coleccion.find({edad: {$gte:20,$lte:40}}) ->
        // {edad:{$gt:40}}
        // {edad: {$get:40},{$lt:60}}
        /*Document filtroEdad = new Document().append("edad", new Document().append("$gt", 45).append("$lt", 60));
        FindIterable resultado = collection.find(filtroEdad); // [{},{},{},{},{},{},{},{}]
        MongoCursor<Document> cursor = resultado.iterator(); // RESULSET
        //   d d d d d d d d d d d
        while (cursor.hasNext()) {
            Document document = cursor.next();
            String nombre = document.getString("nombre");
            String apellido = document.getString("apellido");
            int edad = document.getInteger("edad");
            System.out.println(nombre + " " + apellido + " " + edad);
        }*/
    }
}
