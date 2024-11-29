import model.Producto;

public class Entrada {

    public static void main(String[] args) {
        GestorFicheros gestorFicheros = new GestorFicheros();
        //gestorFicheros.escribirBinarios("src/main/java/resources/datos.bin");
        // gestorFicheros.lecturaBinarios("src/main/java/resources/datos.bin");
        // gestorFicheros.escribirObjeto("src/main/java/resources/objetos.obj");
        // gestorFicheros.escribirObjeto("src/main/java/resources/almacen.obj");
        gestorFicheros.lecturaObjeto("src/main/java/resources/objetos.obj");
        // 12345L;
        // Producto producto = new Producto(1,"sadasd",123.6,21); // caracteristicas del objeto
        // 23456L
                                                                                    // UID 12345L Producto


    }
}
