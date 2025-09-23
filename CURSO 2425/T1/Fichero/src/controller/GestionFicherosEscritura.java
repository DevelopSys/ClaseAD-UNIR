package controller;

import java.io.*;
import java.util.Scanner;

public class GestionFicherosEscritura {

    private int contador=0;
    public void escribirFichero(String path) {
        Scanner scanner = new Scanner(System.in);
        //contador++;
        //path += "_escritura"+contador+".txt";
        // FILE -> FILEWRITER -> BUFFERWRITER -> PRINTWRITER -> CERRAR
        /*System.out.println("Cual es el nombre del fichero a guardad");
        String nombre = scanner.next();*/
        //File file = new File(path+nombre+".txt");
        File file = new File(path+"ejemplo.txt");
        FileWriter fileWriter= null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        /*System.out.println("Por favor introduce lo que quieres guardar");
        String lecturaFrase = scanner.nextLine();
        System.out.println("Indica si quieres sobreescribir el fichero");
        boolean sobreescritura = scanner.nextBoolean();*/

        try {
            fileWriter = new FileWriter(file, true); // append -> anexar o no la escritura
            //bufferedWriter = new BufferedWriter(fileWriter);
            // fileWriter.write(lecturaFrase);
            //bufferedWriter.newLine();
            //bufferedWriter.write(432);
            printWriter = new PrintWriter(file);
            printWriter.println("Linea escrita con un print writer");
            printWriter.println("Otra escrita con un print writer"); // \n


        } catch (IOException e) {

            System.out.println("Error en la escritura del fichero, por permisos");
        } finally {
            try {
                //bufferedWriter.close();
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cierre del flujo");
            }
        }


    }
}
