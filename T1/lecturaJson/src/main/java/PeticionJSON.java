import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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
                System.out.println("Titulo: " + producto.getString("title"));
                System.out.println("Precio: " + producto.getDouble("price"));
                JSONArray tags = producto.getJSONArray("tags");
                System.out.println("Las categorias del producto son:");
                for (Object tag : tags) {
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

    public void metodoMenu() {

        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        // BufferReader br -> lectura por teclar

        do {
            System.out.println("1. Leer");
            System.out.println("2. Buscar");
            System.out.println("3. Filtar");
            System.out.println("4. Exportar");
            System.out.println("5. Salir");
            System.out.println("Que opcion quieres realizar");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Leer JSON");
                    try {
                        URL url = new URL("https://dummyjson.com/products");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
                        JSONArray jsonArray = jsonObject.getJSONArray("products");

                        // primitivas->a, 7, false
                        // complejas -> (9,99 + metodos)

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            String title = product.getString("title");
                            String description = product.getString("description");
                            Double price = product.getDouble("price");
                            int stock = product.getInt("stock");
                            System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                                    , title, price, description);
                        }

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("Buscar elementos");
                    System.out.println("Cual es el id del elemento que buscas");
                    int id = sc.nextInt();
                    filtrarPorId(id);
                    break;
                case 3:
                    System.out.println("Filtrar elementos");
                    System.out.printf("Introduce precio max");
                    int maxA = sc.nextInt();
                    System.out.printf("Introduce precio min");
                    int minA = sc.nextInt();
                    filtrarPrecio(minA, maxA);
                    break;
                case 4:
                    System.out.println("Exportar elementos");
                    exportarDatos();
                    break;

            }

        } while (opcion != 5);

    }

    private void exportarDatos() {
        // File -> FileWriter -> PrintWriter
        File file = new File("src/main/java/resources/productos.txt");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            // Leer JSON -> iterar por producto -> escribe una linea
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");
            // [p1,p2,p3,p4,p5] ->5
            // binding
            // 0,1,2,3,4
            for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject object = jsonArray.getJSONObject(i);
                Producto producto = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),Producto.class);
                // System.out.println("title: "+title+", price:"+ 23.23+"', stick:"+ 23);
                // System.out.printf("title:%s, price:%.2f, stick:%d",title,price,stock);
                String exportacionProducto = String.format("title:%s price:%.2f stock:%d", producto.getTitle(),
                        producto.getPrice(), producto.getStock());
                // printWriter.println(exportacionProducto);
                System.out.println(exportacionProducto);
            }

            System.out.println("Exportacion completada");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)


        } catch (MalformedURLException e) {
            System.out.println("Error en URL");
        } catch (IOException e) {
            System.out.println("Error en la creacion de la escritura");
        } finally {
            try {
                printWriter.close();
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }


    }

    private void filtrarPorId(int id) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);
                if (id == product.getInt("id")) {
                    String title = product.getString("title");
                    String description = product.getString("description");
                    Double price = product.getDouble("price");
                    int stock = product.getInt("stock");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void filtrarPrecio(int min, int max) {
        try {
            URL url = new URL("https://dummyjson.com/products");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(bufferedReader.readLine());
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            // primitivas->a, 7, false
            // complejas -> (9,99 + metodos)
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject product = jsonArray.getJSONObject(i);
                double price = product.getDouble("price");
                if (price < max && price > min) {
                    String title = product.getString("title");
                    String description = product.getString("description");

                    int stock = product.getInt("stock");
                    System.out.printf("El producto %s tiene como precio %.2f y una descripcion de %s\n"
                            , title, price, description);
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
