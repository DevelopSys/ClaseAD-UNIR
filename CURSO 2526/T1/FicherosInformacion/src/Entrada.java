import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Entrada {
    public static void main(String[] args) {
        Operaciones operaciones = new Operaciones();
        // operaciones.informacionFichero("src/resources/ejemplo1.txt");
        // operaciones.informacionDirectorio("src/resources");
        // operaciones.escribirFichero("src/resources/escritura.txt");
        // operaciones.escrituraSuperior("src/resources/escritura_superior.txt");
        // operaciones.mostrarInfoDirectorios(new File("/Users/borja/Documents/GitHub/ClaseAD-UNIR/CURSO 2526/T1/FicherosInformacion"));
        // operaciones.exportarUsuario("src/resources/usuarios.csv");
        operaciones.lecturaFichero("src/resources/escritura.txt");


    }
}
