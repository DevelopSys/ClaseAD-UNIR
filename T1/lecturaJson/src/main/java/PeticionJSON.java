import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PeticionJSON {

    public void procesarPeticion() {

        // URL -> HTTPCONNECTION -> BUFFEREADER
        String urlString = "https://dummyjson.com/products";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String linea = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((linea = bufferedReader.readLine()) != null) {
                stringBuffer.append(linea);
            }

            JSONObject peticionProducto = new JSONObject(stringBuffer.toString());
            JSONArray listaProductos = peticionProducto.getJSONArray("products");
            ArrayList<String> categorias = new ArrayList<>();
            for (Object item : listaProductos) {
                // item es un JSONObject -> YO LO SÉ
                JSONObject producto = (JSONObject) item;
                System.out.println("Titulo: "+producto.getString("title"));
                System.out.println("Precio: "+producto.getDouble("price"));
                JSONArray tags = producto.getJSONArray("tags");
                System.out.println("Las categorias del producto son:");
                for (Object tag: tags ) {
                    //System.out.println("\t"+tag);
                    categorias.add(tag.toString());
                }

            }



        } catch (MalformedURLException e) {
            System.out.println("No es una web, por favor intentalo de nuevo");
        } catch (IOException e) {
            System.out.println("Error en la pagina, no responde");
        }

    }

}
