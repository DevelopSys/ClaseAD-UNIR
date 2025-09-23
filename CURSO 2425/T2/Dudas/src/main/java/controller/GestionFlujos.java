package controller;

import model.Usuario;

import java.io.*;
import java.security.spec.RSAOtherPrimeInfo;

public class GestionFlujos {

    // File el fichero con el que trabajo
    private static File file = new File("src/main/java/resources/ejemplo_dudas.txt");
    private static File fileBinario = new File("src/main/java/resources/ejemplo_dudas.bin");
    private static File fileObjeto = new File("src/main/java/resources/ejemplo_dudas.obj");
    // obtener info del fichero

    // TEXTO PLANO -> "asdasdasdasd" .txt
    public void escrituraTPlano(){
        // Escritura -> Output -> Writer
        // File -> FileWriter
        // File -> FileWriter -> BufferedWritter
        // File -> FileWriter -> PrintWritter
        // FileWriter fileWriter = null;
        // BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            /*fileWriter = new FileWriter(file, true);
            fileWriter.write("Esto es un ejemplo de escritura1\n");
            fileWriter.write("Esto es un ejemplo de escritura2\n");
            fileWriter.write("Esto es un ejemplo de escritura3\n");
            fileWriter.write("Esto es un ejemplo de escritura4\n");*/
            /*bufferedWriter = new BufferedWriter(new FileWriter(file,true));
             bufferedWriter.write("Esto es una linea con BW");
             bufferedWriter.newLine();*/
            printWriter = new PrintWriter(new FileWriter(file,true));
            printWriter.println("Esta linea va con PW");

        } catch (IOException e) {
            System.out.println("Error en la puesta en escritura del fichero");
        } finally {
            try {
                // fileWriter.close();
                //bufferedWriter.close();
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }
    public void lecturaTPlano(){
        // File
        // File -> FileReader -> lectura caracter a caracter
        // File -> FileReader -> BufferedReader -> linea a linea (hasta encontrar un salto de linea)

        // FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea = null;
            while ((linea = bufferedReader.readLine()) != null){
                System.out.print(linea);
            }

            /*fileReader = new FileReader(file);
            int lectura = -1;
            while ( (lectura = fileReader.read()) > -1 ){
                System.out.println((char) lectura); // -1 o el caracter
            }*/
        } catch (FileNotFoundException e) {
            System.out.println("Error en la ruta del fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } finally {
            try {
                // fileReader.close();
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    // BINARIO -> tipo de dato 5 false
    public void lecturaBinarios(){
        // File -> FileReader -> BufferedReader
        // File -> FileInput -> DataInputStream


        DataInputStream dataInputStream = null;

        try {

            dataInputStream = new DataInputStream(new FileInputStream(file));
            char letra = dataInputStream.readChar();
        } catch (FileNotFoundException e) {
            System.out.println("Error en la ruta del fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("error en el cerrado");
            }
        }
    }
    public void escrituraBinarios(){
        // File -> FileWritter -> BufferedWritter
        // File -> FileOutput -> DataOutputStreom
        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(fileBinario));
            dataOutputStream.writeUTF("Esto es un ejemplo de binario");
        } catch (FileNotFoundException e) {
            System.out.println("Error en la ruta del fichero");
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    // OBJETO -> Objecto(asd,asd,asd)
    public void lecturaObjetos(){
        // File -> FileInputStream -> ObjectInputStream
        // usuario -> 123123123L

        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));

            Object recuperado = null;
            while ((recuperado = objectInputStream.readObject())!=null){
                System.out.println(((Usuario)recuperado).getNombre());
            }

        } catch (IOException e) {
            System.out.println("Error en la lectura");
        } catch (ClassNotFoundException e) {
            System.out.println("No se puede encontrar la clase con la que mapear");
        }
        finally {
            try {
                objectInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }
    public void escrituraObjetos(){
        // File -> FileOutputStream -> ObjectOutputStream

        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
            objectOutputStream.writeObject(new Usuario("Borja","Martin","correo@gmail"));
        } catch (IOException e) {
            System.out.println("error en la escritura");
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en la escritura");
            }
        }
    }

}
