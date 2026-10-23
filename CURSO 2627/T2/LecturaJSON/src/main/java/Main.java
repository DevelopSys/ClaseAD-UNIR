import com.google.gson.Gson;
import model.Product;
import model.Shop;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 0 creo el cliente
        // 1 creo la peticion
        // 2 lanzar la peticion
        // 3 analizas la respuesta

        HttpClient client = HttpClient.newHttpClient(); // navegador
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummyjson.com/products"))
                .GET()
                .build();
        try {
            Gson gson = new Gson();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Peticion procesada");
            JSONObject jsonObject = new JSONObject(response.body());
            Shop shop = gson.fromJson(jsonObject.toString(), Shop.class);
            // shop.getProducts();
            /*JSONArray jsonArray = jsonObject.getJSONArray("products");
            List<Product> lista = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonProduct = jsonArray.getJSONObject(i);
                Product product = gson.fromJson(jsonProduct.toString(),Product.class);
                // System.out.printf("Producto: %s Precio: %.1f\n",title,price);
                // Product product = new Product(title,price);
                lista.add(product);
            }*/


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
