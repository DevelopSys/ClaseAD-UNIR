import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

public class Entrada {

    public static void main(String[] args) {
        File file = new File("src/main/java/resources/usuario.txt");
        // txt -> JSON
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuffer lecturaCompleta = new StringBuffer();
            String linea = null;

            while ((linea=bufferedReader.readLine())!=null){
                lecturaCompleta.append(linea);
            }

            JSONObject usuario = new JSONObject(lecturaCompleta.toString());
            Object nombreUsuario = usuario.get("nombre");
            JSONArray asignaturas = usuario.getJSONArray("asignaturas");
            for ( Object item : asignaturas  ) {
                System.out.println(item);
            }

            System.out.println(asignaturas);

        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        } catch (IOException e) {
            System.out.println("Error al leer");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerradp");
            }
        }
    }
}
