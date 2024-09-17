import javax.lang.model.SourceVersion;
import java.io.File;
import java.io.IOException;

public class Entrada {

    public Entrada() {
    }

    public static void main(String[] args) throws IOException {
        // FILE -> fichero logico -> fisico
        // fichero logico
        // File ficheroSinPuntero = new File("/Users/borja/Documents/GitHub/ClaseAD-UNIR/T1/Fichero/src/resources/directorio/ejemplo_fichero.md");
        /*File ficheroSinPuntero = new File("src/resources/directorio");
        System.out.println(ficheroSinPuntero.getName());
        System.out.println(ficheroSinPuntero.getParent());
        System.out.println(ficheroSinPuntero.length());
        System.out.println(ficheroSinPuntero.exists());
        System.out.println(ficheroSinPuntero.isDirectory());
        // File[] -> todos los FICHEROS que estan dentro del directorio
        ficheroSinPuntero.listFiles();
        // String[] -> todas las rutas de los FICHEROS que estan dentro del directorio
        ficheroSinPuntero.list();*/

        // 1 -> Obtener todos los nombre de los ficheros del directorio llamado directorio
        // 2 -> Crear una subcarpeta en el directorio llamado directorio y crear en ella un fichero
        //   -> Obtener todos los nombre de los ficheros del directorio llamado directorio y el subdirectorio creado
        // 3 -> Listar el nombre de todos los ficheros del SISTEMA ( C:/User o /Users )

        System.out.println("\n*****Ejercicio Acceso a datos********");

        File carp = new File("T1/Fichero/src/resources/directorio");

        String[] contenido = carp.list();
        System.out.println("Ficheros del directorio -------> directorio: " + contenido.length);
        for (String nombre : contenido) {
            System.out.println(nombre);

        }

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        File fich = new File("T1/Fichero/src/resources/directorio/directorio/ficheronuevo.txt");
        File carpeta = fich.getParentFile();

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        boolean fichero = fich.createNewFile();
        if (fichero)
            System.out.println("\nEl fichero se ha creado con éxito");
        else
            System.out.println("\nEl fichero no ha podido crearse");

        String[] contenido1 = carpeta.list();
        System.out.println("\nArchivos o carpetas que contiene : " + contenido1.length);
        for (String nombre : contenido1) {
            System.out.println(nombre);


        }
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        File[] contenido2 = carpeta.listFiles();
        System.out.println("\nCarpetas que contiene : " + contenido2.length);
        for (File nombre : contenido2) {
            System.out.println(nombre);


        }


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        File sistema = new File("C:/users");
        String[] ficheros = sistema.list();
        System.out.println("\nArchivos o carpetas que contiene: " + ficheros.length);

        for (String nombre : ficheros) {
            File f = new File(sistema.getPath(), nombre);
            if (f.isDirectory()) {
                System.out.println(nombre + ", " + " carpeta");
            } else {
                System.out.println(nombre + ", " + " fichero, " + f.length() + " bytes");
            }
        }



    }
}



// RECURSIVIDAD


        /*if (!ficheroSinPuntero.exists()) {
            try {
                ficheroSinPuntero.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en la creacion del fichero");
            }
        }*/



