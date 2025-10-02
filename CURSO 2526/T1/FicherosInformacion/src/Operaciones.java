import model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Operaciones {
    public void informacionFichero(String path) {
        File file = new File(path); // logico

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Hay un problema con la entrada / salida");
            }
        } else {
            // obtener su informacion
            System.out.println(file.exists());
            System.out.println(file.isFile());
            System.out.println(file.canWrite());
            System.out.println(file.canRead());
            System.out.println(file.canExecute());
            System.out.println(file.isDirectory());
        }
    }

    public void mostrarInfoDirectorios(File file) {
        System.out.println("El nombre de la carpeta/fichero a analizar es " + file.getName());
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                mostrarInfoDirectorios(item);
            }
        }

    }

    public void mostrarInfoDirectorios(String path) {
        File file = new File(path);
        System.out.println("El nombre de la carpeta/fichero a analizar es " + file.getName());
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                mostrarInfoDirectorios(item.getPath());
            }
        }
    }

    public void escribirFichero(String path) {
        File file = new File(path);
        FileWriter fileWriter = null;
        String mensajeCifrar = "Este mensaje es oculto y sera el enunciado del examen final";
        try {
            fileWriter = new FileWriter(file, true);
            for (int i = 0; i < mensajeCifrar.length(); i++) {
                char letra = mensajeCifrar.charAt(i);
                fileWriter.write((int) letra * 5);
            }
        } catch (IOException e) {
            System.out.println("No puedes realizar la escritura");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void escrituraSuperior(String path) {
        File file = new File(path);

        PrintWriter printWriter = null;
        // BufferedWriter bufferedWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.println("Esto es un ejemplo con print writer");
            printWriter.println("Esto es una nueva linea");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    public void exportarUsuario(String path) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario(1, "Borja1", "Martin1", "123A", "borja@gmail.com"));
        listaUsuarios.add(new Usuario(2, "Borja2", "Martin2", "123A", "borja@gmail.com"));
        listaUsuarios.add(new Usuario(3, "Borja3", "Martin3", "123A", "borja@gmail.com"));
        listaUsuarios.add(new Usuario(4, "Borja4", "Martin4", "123A", "borja@gmail.com"));
        listaUsuarios.add(new Usuario(5, "Borja5", "Martin5", "123A", "borja@gmail.com"));
        listaUsuarios.add(new Usuario(6, "Borja6", "Martin6", "123A", "borja@gmail.com"));

        File file = new File(path);
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.println("id,nombre,apellido,dni,correo");
            for (Usuario usuario : listaUsuarios) {
                printWriter.println(usuario);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            printWriter.close();
        }
    }

    public void lecturaFichero(String path) {
        File file = new File(path);
        FileReader reader = null;

        try {
            reader = new FileReader(file);
            // continua con la lectura hasta que -1
            int lectorCodigo = 0;
            while ((lectorCodigo = reader.read()) != -1) {
                System.out.print((char) (lectorCodigo / 5));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void escribirObjeto(String path) {

        Scanner lector = new Scanner(System.in);
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(new Usuario(1, "Borja", "Martin", "1234", "borja@unir.com"));
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de escritura");
        } finally {
            try {
                oos.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado del fichero");
            }
        }

        /*
        boolean fallo = false;
        do {
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {

                System.out.println("El fichero no existe. queres crearlo");
                // true false
                boolean crear = lector.nextBoolean();
                if (crear) {
                    fallo = true;
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        System.out.println("No tienes permisos de crear ficheros");
                    }
                }
            }
        } while (fallo);*/


    }

    public void leerObjeto(String path) {
        File file = new File(path);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        // cliente (1, "Borja", "Martin")
        // pedido (1,1,"Pedido de camisaetas",2123123)

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Usuario usuario = null;


            while ((usuario = (Usuario) ois.readObject()) != null) {
                System.out.println(usuario.getNombre());
                System.out.println(usuario.getCorreo());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println("Error en la clase de lectura");
        } finally {
            try {
                ois.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }
}

// en el programa, SI EXISTE EL FICHERO, importar en un arraylist todos los usuarios del mismo
// si el programa cierra, SI EXISTE EL FICHERO, anexa informacion
// si el programa cierra, SI EL FICHERO NO EXISTE, lo crea y guarda informacion
