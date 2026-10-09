package controller;

import model.Persona;
import model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileContoller {

    private String basePath = "src/resources/";

    public void crearFichero(String path) throws IOException {
        // src/resources/example.txt
        // directorio (carpeta) fichero_final (alemento con extension)
        File file = new File(basePath + path);
        if (file.exists()) {
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

    public void infoFichero(String path) {
        File file = new File(basePath + path); // src/resources/
        /*System.out.println(file.exists());
        System.out.println(file.canWrite());
        System.out.println(file.canExecute());
        System.out.println(file.length());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.getAbsolutePath());*/
        // file.listFiles(); // Files[]
        // file.list(); // String[]
        /*String[] listadoRutas = file.list();
        for (String item: listadoRutas){
            // System.out.println(item);
            if (item.contains("info")){
                System.out.println("El fichero o carpeta de informacion esta presente");
            }
        }*/
        File[] ficheros = file.listFiles();
        /*for (File item : ficheros) {
            if (!item.isHidden()) {
                System.out.println(item.getAbsolutePath());
                System.out.println(item.getName());
            }
        }*/
        // funcion escrita diferente { p -> }
        // public static Stream<T>
        // list -> recorro stream -> si cumple hago add a la lista
        /*Arrays.stream(ficheros).forEach(item -> {
            if (!item.isHidden()) System.out.println(item);
        });*/
        List<File> listaFiltrada =
                Arrays.stream(ficheros).filter(File::isHidden).toList();

    }

    public void listadoTotal(String path) {
        File file = new File(basePath + path);
        for (File item : file.listFiles()) {
            System.out.println(item.getAbsolutePath());
            if (item.isDirectory()) {
                listadoTotal(basePath + path + "/" + item.getName());
            }
        }
    }

    public boolean borrarFichero(String path) {
        File file = new File(basePath + path);
        return file.delete();
    }

    public boolean renombrarFichero(String path, String name) {
        File file1 = new File(basePath + path);
        File file2 = new File(basePath + name);
        return file1.renameTo(file2);
    }

    public void escrituraFicheroTXT(String path) {
        File file = new File(basePath + path);
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        ArrayList<Persona> listado = new ArrayList<>();
        listado.add(new Persona("Nombre1", "Apellido1", 123, "correo1"));
        listado.add(new Persona("Nombre2", "Apellido2", 123, "correo2"));
        listado.add(new Persona("Nombre3", "Apellido3", 123, "correo3"));
        listado.add(new Persona("Nombre4", "Apellido4", 123, "correo4"));

        try {
            // fw = new FileWriter(file, true); // bit byte string
            pw = new PrintWriter(new FileWriter(file, true));
            PrintWriter finalPw = pw;
            listado.forEach(item -> finalPw.println(item.toCSV()));
            // ejecuciones
        } catch (IOException e) {
            System.out.println("Error en la generacion del writer");
        } finally {
            Objects.requireNonNull(pw).close();
        }

        /*
        try(FileWriter fw = new FileWriter(file)) {
            // fw = new FileWriter(file);
            // ejecuciones
        } catch (IOException e) {
            System.out.println("Error en la generacion del writer");
        }
         */
    }

    public void lecturaFicheroTXT(String path) {
        File file = new File(basePath + path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        /*
        try (FileReader fileReader = new FileReader(file)) {

            // el fileReader queda cerrado
        } catch (IOException e){

        }*/
        try {
            fileReader = new FileReader(file); // bit a bit
            bufferedReader = new BufferedReader(fileReader); // string a string
            String linea = null;
            // int code = fileReader.read(); -1
            // int 69-> char E
            /*int code = -1;
            while ( (code = fileReader.read()) != -1){
                System.out.print((char) code);
            }*/
            StringBuilder builder = new StringBuilder();
            while ((linea = bufferedReader.readLine())!=null){
                builder.append(linea+"\n");
            }
            System.out.println(builder.toString());




        } catch (FileNotFoundException e) {
            System.out.println("La ruta es invalida");
        } catch (IOException e) {
            System.out.println("No hay permisos de lectura");
        } finally {
            try {
                Objects.requireNonNull(bufferedReader).close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero");
            }
        }


    }

    public List<Persona> importarCSV(String path){
        List<Persona> lista = new ArrayList();
        File file = new File(basePath+path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linea = bufferedReader.readLine();
            while ((linea = bufferedReader.readLine())!=null){
                // public Persona(String nombre, String apellido, int edad, String mail) {
                String[] datos = linea.split(",");
                Persona p = new Persona(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
                lista.add(p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void escribirObjetos(String path){
        File file = new File(basePath+path);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(new Usuario("Borja","Martin","1234A"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lecturaObjetos(String path){
        File file = new File(basePath+path);
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            Usuario usuario = (Usuario) ois.readObject();
            System.out.println(usuario);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Conversion en UID no correcta");
        } catch (ClassCastException e){

        }
    }
}
