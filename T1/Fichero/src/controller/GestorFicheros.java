package controller;

import java.io.File;

public class GestorFicheros {

    // src/resources/directorio
    public void lecturaDirectorios(String path) {
        // File (logico) -> file (fisico)
        File file = new File(path);
        String[] nombres = file.list();
        File[] ficheros = file.listFiles();
        for (File item : ficheros) {
            //if (!item.isHidden()){ -> solo mostrar los que no son ocultos
            System.out.println(item.getName());
            if (item.isDirectory()) {
                File[] subdirectorio = item.listFiles();
                for (File file1 : subdirectorio) {
                    System.out.println("\t" + file1.getName());
                }
            }

        }
        /*for (String item : nombres) {
            if (!(item.charAt(0) == '.')) {
                System.out.println(item);
            }
        }*/

        // File file = new File("/path");
        // System.out.println(file.getName());

    }

    public void lecturaRecursiva(String path) {
        File file = new File(path); // paso a un fichero logico y fisico
        //File[] ficheros = file.listFiles();
        for (File item : file.listFiles()) {
            System.out.println(item.getName());
            if (item.isDirectory()) {
                lecturaSubdirectorios(item);
            }
            // pregunto si es directorio
            // saco todos los ficheros del directorio y los muestro -> NO SE CUANTAS VECES PREGUNTO
        }
    }

    // RECURSIVIDAD -> EJECUCION QUE SE LLAMA A ELLA MISMA, CON CUIDADO TENGA UNA SALIDA
    private void lecturaSubdirectorios(File fichero) {
        for (File file : fichero.listFiles()) {
            System.out.println("\t"+file.getName());
            if (file.isDirectory()) {
                lecturaSubdirectorios(file);
            }
        }
    }
}
