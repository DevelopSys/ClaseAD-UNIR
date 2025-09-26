import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Entrada {
    public static void main(String[] args) {
        Operaciones operaciones = new Operaciones();
        // operaciones.informacionFichero("src/resources/ejemplo1.txt");
        // operaciones.informacionDirectorio("src/resources");
        operaciones.escribirFichero("src/resources/escritura.txt");

    }
}
