import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void informacionDirectorio(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("Vamos a trabajar con directorio");
            System.out.println("La ruta de este directorio es " + file.getAbsolutePath());
            // cuantos ficheros tengo dentro del directorio
            // Saca la lista de nombres que hay en la carpeta
            String[] nombreFicheros = file.list();
            File[] ficheros = file.listFiles();
            for ( File item : ficheros ) {
                if(!item.isHidden()){
                    System.out.println(item.getAbsolutePath());
                }
            }
            /*for (String item : nombreFicheros) {
                if (item.charAt(0) != '.') {
                    System.out.println(item);
                }
            }*/

        } else if (!file.exists()) {
            // solo crea el directorio si la ruta padre existe
            file.mkdir();
            // crea la ruta completa
            //file.mkdirs();
        }
    }

    public void mostrarFicherosRecurrente(){
        // Mostrar todos los ficheros de una ruta y hacerlo de forma recursiva
    }

    public void escribirFichero(String path){
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(875);
        } catch (IOException e) {
            System.out.println("No puedes realizar la escritura");
        }  finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
