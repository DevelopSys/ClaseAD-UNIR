import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import model.RespuestaProductos;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainLectorJSON {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL("https://dummyjson.com/products");
            RespuestaProductos respuestaProductos = mapper.readValue(url, RespuestaProductos.class);
            for (Product item: respuestaProductos.getProducts()) {
                System.out.println(item);
            }


        } catch (MalformedURLException e) {
            System.out.println("Error en la URL indicada");
        } catch (StreamReadException e) {
            System.out.println("Error en la lectura. No es un JSON");
        } catch (DatabindException e) {
            System.out.println("Error en el mapeado del json");
        } catch (IOException e) {
            System.out.println("Error en la conexion de red");
        }
    }
}
