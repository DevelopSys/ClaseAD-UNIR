
import controller.GestionFicherosEscritura;
import controller.GestorFicherosConjuntos;
import controller.GestorFicherosLectura;


import java.util.Scanner;

public class Entrada {



    public static void main(String[] args) throws IOException {


        //GestorFicherosLectura gestorFicherosLectura = new GestorFicherosLectura();
        // gestorFicheros.lecturaDirectorios("src/resources/directorio");
        // gestorFicheros.lecturaDirectorios("src/resources/ficheros");
        // gestorFicheros.lecturaRecursiva("/users/borja/documents/github");
        //gestorFicherosLectura.lecturaTextoPlano("src/resources/ficheros/lectura.txt");
        //GestionFicherosEscritura gestionFicherosEscritura = new GestionFicherosEscritura();
        //gestionFicherosEscritura.escribirFichero("src/resources/ficheros/");
        GestorFicherosConjuntos gestorFicherosConjuntos = new GestorFicherosConjuntos();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {


            System.out.println("Introduce que quieres hacer");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    gestorFicherosConjuntos.cifrado("src/resources/ficheros/cifradoCodigo.txt");
                    break;
                case 2:
                    gestorFicherosConjuntos.descifrarMensajeCodigo("src/resources/ficheros/cifradoCodigo.txt");
                    break;
            }
        } while (opcion!=0);


        // gestorFicherosConjuntos.cifrado("src/resources/ficheros/cifrado.txt");


        // instancias de objetos y llamadas a metodos
        // OBJETO CLASE 1
        // OBJETO CLASE 2

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



