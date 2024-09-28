import controller.GestionFicherosEscritura;
import controller.GestorFicherosConjuntos;
import controller.GestorFicherosLectura;

import java.util.Scanner;

public class Entrada {

    public static void main(String[] args) {

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
        ficheroSinPuntero.list();

        // 1 -> Obtener todos los nombre de los ficheros del directorio llamado directorio
        // 2 -> Crear una subcarpeta en el directorio llamado directorio y crear en ella un fichero
        //   -> Obtener todos los nombre de los ficheros del directorio llamado directorio y el subdirectorio creado
        // 3 -> Listar el nombre de todos los ficheros del SISTEMA ( C:/User o /Users )

        // RECURSIVIDAD


        /*if (!ficheroSinPuntero.exists()) {
            try {
                ficheroSinPuntero.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en la creacion del fichero");
            }
        }*/
    }
}
