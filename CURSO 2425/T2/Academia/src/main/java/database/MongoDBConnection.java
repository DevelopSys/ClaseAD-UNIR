package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Curso;
import model.Usuario;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBConnection {
    private String connectionString = "mongodb+srv://%s:%s@cluster0.cg3in.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private MongoClient mongoClient;
    private CodecProvider codecProvider;
    CodecRegistry codecRegistry;

    public MongoDBConnection() {

        // todos los datos que tu traduzcas son de un tipo concreto
        // INSERCIONES -> Usuario
        // INSERCIONES -> Coches
        // INSERCIONES -> Ciclos
        // SELECT -> List<Usuarios>
        // CodeProvider -> Pojo
        codecProvider = PojoCodecProvider.builder().automatic(true).build();
        codecRegistry = CodecRegistries.fromRegistries(
                MongoClients.create().getCodecRegistry(),
                CodecRegistries.fromProviders(codecProvider)
        );
        mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));
    }

    public MongoCollection getUserCollecion() {
        MongoDatabase database = mongoClient.getDatabase("academia").withCodecRegistry(codecRegistry);
        return database.getCollection("usuarios", Usuario.class);
    }

    public MongoCollection getCursosCollection(){
        MongoDatabase database = mongoClient.getDatabase("academia").withCodecRegistry(codecRegistry);
        return database.getCollection("cursos",Curso.class);
    }
}
