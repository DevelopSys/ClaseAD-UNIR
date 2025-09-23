import model.Producto;
import model.Tienda;

import java.io.*;

public class GestorFicheros {

    public void escribirBinarios(String path) {

        // File
        File file = new File(path);
        DataOutputStream dataOutputStream = null;

        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeUTF("Esto es un ejemplo");
            dataOutputStream.writeInt(5);
            // 0x0A -> byte
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChar(234);
        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Problema con el fichero");
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }


    }

    public void lecturaBinarios(String path) {

        // File
        File file = new File(path);
        DataInputStream dataInputStream = null;

        try {
            dataInputStream = new DataInputStream(new FileInputStream(file));
            String lectura = dataInputStream.readUTF();
            System.out.println(lectura);
            // chars
            int numero = dataInputStream.readInt();
            System.out.println(numero);
            // int
            boolean valor = dataInputStream.readBoolean();
            System.out.println(valor);
            // bool
            char letra = dataInputStream.readChar();
            System.out.println(letra);
            // char


        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Problema con el fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }


    }

    public void escribirObjeto(String path) {
        File file = new File(path);
        ObjectOutputStream objectOutputStream = null;


        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(new Producto(1, "Producto1", 98.65, 1, 23, "apple"));
            objectOutputStream.writeObject(new Tienda("Tienda1",123,100));
            objectOutputStream.writeObject(new Tienda("Tienda1",123));

        } catch (IOException e) {
            System.out.println("Error en el fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar");
            }
        }
    }

    public void lecturaObjeto(String path) {
        File file = new File(path);
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Producto producto = (Producto) objectInputStream.readObject(); // null 12345L
            // Producto = 2345L
            producto.mostrarDatos();
            Tienda tienda = (Tienda) objectInputStream.readObject(); // null 12345L
            tienda.mostrarDatos();
            Tienda tienda1 = (Tienda) objectInputStream.readObject(); // null 12345L
            tienda1.mostrarDatos();

        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase destino");
        } catch (ClassCastException e) {
            System.out.println("Error al declarar el tipo de datos");
        }

    }
}
