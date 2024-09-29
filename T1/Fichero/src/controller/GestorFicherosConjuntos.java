package controller;

import java.io.*;
import java.sql.Statement;
import java.util.Scanner;

public class GestorFicherosConjuntos {

    public void lecturaEscritura(String path){
        Scanner scanner = new Scanner(System.in);
        File file = new File(path);
        FileWriter fileWriter = null;

        System.out.println("Por favor, introduce el mensaje que quieres guardar");
        String mensaje = scanner.nextLine();
        System.out.println("Por favor indica la fase de cifrado del mensaje");
        int fase = scanner.nextInt();
        //este es el contenido del examen

        try {
            fileWriter = new FileWriter(file,false);
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i);
                //fileWriter.write(letra+"\n");
                int codigo = (int)letra;
                // System.out.println("codigo = " + codigo);
                //fileWriter.write("123");
                fileWriter.write(String.valueOf(codigo*fase)+"\n");
            }
        } catch (IOException e) {
            System.out.println("Error en los permisos");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cerrado");
            }
        }


    }


    public void cifrado(String path){
        Scanner scanner = new Scanner(System.in);
        File file = new File(path);
        FileWriter fileWriter = null;

        System.out.println("Por favor, introduce el mensaje que quieres guardar");
        String mensaje = scanner.nextLine();
        System.out.println("Por favor indica la fase de cifrado del mensaje");
        int fase = scanner.nextInt();
        //este es el contenido del examen

        try {
            fileWriter = new FileWriter(file,false);
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i);
                //fileWriter.write(letra+"\n");
                int codigo = (int)letra;
                // System.out.println("codigo = " + codigo);
                //fileWriter.write("123");
                char codigoLetra = (char)(codigo*fase);
                //fileWriter.write(String.valueOf(codigo*fase)+"\n");
                fileWriter.write(codigoLetra);
            }
        } catch (IOException e) {
            System.out.println("Error en los permisos");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cerrado");
            }
        }
    }
    public void descifrarMensaje(String path){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor indica la fase de descifrado");
        int fase = scanner.nextInt();

        File file = new File(path);
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String lectura = null;
            while ((lectura= br.readLine())!=null){
                // lectura
                int codigo = Integer.parseInt(lectura);
                System.out.print((char) (codigo/fase));
            }
            System.out.println();




        }catch (IOException e){

        }


    }

    public void descifrarMensajeCodigo(String path){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor indica la fase de descifrado");
        int fase = scanner.nextInt();

        File file = new File(path);
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(file));
            String lectura = null;
            StringBuffer lecturaConjunta = new StringBuffer();
            while ((lectura= br.readLine())!=null){
                // lectura
                lecturaConjunta.append(lectura);
            }
            System.out.println(lecturaConjunta);
            for (int i = 0; i <lecturaConjunta.length(); i++) {
                int codigo = (int)lecturaConjunta.charAt(i)/fase;
                System.out.print((char) codigo);
            }
            System.out.println();

        }catch (IOException e){

        }


    }
}
