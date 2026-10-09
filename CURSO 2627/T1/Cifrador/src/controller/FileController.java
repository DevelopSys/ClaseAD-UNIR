package controller;

import java.io.*;
import java.util.Arrays;

public class FileController {

    private String basePath = "src/resources/";
    private File file;
    // private FileWriter fw; // caracter a caracter
    // private FileReader fr; // caracter a caracter
    // private BufferedReader br; // linea a linea
    public void cifrarMensaje(String mensaje, int fase){
        // este es el examen de acceso a datos
        file = new File(basePath+"mensaje.txt");
        try (FileWriter fileWriter = new FileWriter(file)){
            for (int i = 0; i < mensaje.length(); i++) {
                char letra = mensaje.charAt(i); // e
                int codigo = letra; // e -> 69
                fileWriter.write(codigo*fase);
            }

        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }


    }
    public void descifrarMensaje(int fase){
        file = new File(basePath+"mensaje.txt");
        try (FileReader fr = new FileReader(file)){
            int codigo = -1;
            while ((codigo = fr.read())!=-1){
                System.out.print((char) (codigo / fase));
            }
            // close
        } catch (FileNotFoundException e) {
            System.out.println("Error en la ruta de lectura");
        } catch (IOException e) {
            System.out.println("Error en la accion de lectura");
        }
    }
    public void descifrarASCI(){
        file = new File(basePath+"codigos.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = null;
            while ((linea = br.readLine())!= null){
                String[] codigos = linea.split(" ");
                for (String item: codigos) {
                    int codigo = Integer.parseInt(item);
                    System.out.print((char) codigo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
    }
}
