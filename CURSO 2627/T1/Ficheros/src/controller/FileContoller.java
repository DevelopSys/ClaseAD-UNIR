package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileContoller {

    private String basePath = "src/resources/";

    public void crearFichero(String path) throws IOException {
        // src/resources/example.txt
        // directorio (carpeta) fichero_final (alemento con extension)
        File file = new File(basePath + path);
        if(file.exists()){
            System.out.println("El fichero ya existe");
        } else {
            file.createNewFile();
        }
    }

    public void crearCarpeta(String path) {
        // src/resources/example.txt
        // directorio (carpeta) fichero_final (alemento con extension)
        File file = new File(basePath + path);
        file.mkdirs();
    }
}
