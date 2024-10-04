import model.Producto;

public class Entrada {

    public static void main(String[] args) {
        GestorFicheros gestorFicheros = new GestorFicheros();
        //gestorFicheros.escribirBinarios("src/main/java/resources/datos.bin");
        // gestorFicheros.lecturaBinarios("src/main/java/resources/datos.bin");
        gestorFicheros.escribirObjeto("src/main/java/resources/objetos.obj");

    }
}
