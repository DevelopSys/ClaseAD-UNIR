package controller;

import java.io.*;

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
            System.out.println("\t" + file.getName());
            if (file.isDirectory()) {
                lecturaSubdirectorios(file);
            }
        }
    }

    public void lecturaTextoPlano(String path) {
        // FILE -> FILEREADER -> BUFFEREDREADER  -> cuando se terminan SE CIERRAN
        File file = new File(path);
        //FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        if (file.exists() && file.isFile()) {
            // existe y ademas es un fichero
            // trato la excepcion aqui
            try {
                //fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(new FileReader(file));
                /* LECTURA CON FILEREADER
                int lectura=0;
                // mientras exista el numero haz la lectura e imprime
                while ((lectura = fileReader.read())!=-1){
                    // imprime
                    System.out.print((char) lectura);
                }*/
                String lectura = null;
                // String lecturaCompleta = null;
                // StringBuffer lecturaCompleta = new StringBuffer(); -> sincronizado (si tengo varios hilos, se
                // cominucan entre ellos para decir quien tiene el objeto ocupado
                StringBuilder lecturaCompleta = new StringBuilder(); // solo lo va a utilizar un hilo
                while ((lectura = bufferedReader.readLine())!=null){
                    //System.out.println(lectura);
                    //lecturaCompleta+=lectura;
                    lecturaCompleta.append(lectura);
                    lecturaCompleta.append("\n");
                }
                System.out.println(lecturaCompleta.toString());
                // JSON
                // toda la lectura
                /*int lectura = fileReader.read(); // int -> UNICODE ASCI
                System.out.print((char) lectura);
                lectura = fileReader.read(); // int -> UNICODE ASCI
                System.out.print((char) lectura);
                lectura = fileReader.read(); // int -> UNICODE ASCI
                System.out.print((char) lectura);*/

            } catch (FileNotFoundException e) {
                System.out.println("Fallo en la lectura");
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error en la escritura, fallo de permisos");
            } finally {
                // se ejecuta SI o SI
                try {
                    bufferedReader.close(); // NULLPOINTEREXCEPTION
                } catch (IOException e) {
                    System.out.println("Error en el cerrado del flujo");
                    System.out.println(e.getMessage());
                    // por favor introduce una ruta valida
                } catch (Exception e) {
                    System.out.println("Error en el cerrado por ser nulo");
                }
            }
        }

    }
}
