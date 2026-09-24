package controller;

import java.io.File;
import java.io.IOException;

public class FileContoller {

    public void createFile(String path){
        // path -> src/resources
        // fichero logico -> solo existe en memoria
        File file = new File(path+"file.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error en la creacion");
        }
        System.out.println(file.isDirectory());

    }

}
