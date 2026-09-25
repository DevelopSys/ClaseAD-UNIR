import controller.FileContoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Entrada {

    // main(String[]args){}
    // main(){}
    public static void main(String[] args) {
        FileContoller fileContoller = new FileContoller();

        // lecturas por consola - fichero
        Scanner lector = new Scanner(System.in);
        // lecturas por consola -> solo se pueden leer string
        // BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Proyecto de gestion de ficheros");
        /*
        Creacion y tratamiento de error en fichero
        do {
            System.out.println("Introduce el nombre del fichero");
            String nombreFichero = lector.nextLine();
            try {
                fileContoller.crearFichero(nombreFichero);
                break;
            } catch (IOException e) {
                System.out.println("Error a la hora de crear el fichero");
            }
        }while (true);
        System.out.println("Fichero creado correctamente");
        // terminamos el uso del flujo -> cerrando el flujo*/
        String nombre = lector.nextLine();
        fileContoller.crearCarpeta(nombre);
        lector.close();

    }

}