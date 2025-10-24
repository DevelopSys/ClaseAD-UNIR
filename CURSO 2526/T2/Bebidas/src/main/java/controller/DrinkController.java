package controller;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Drink;
import model.ResponseDrinks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DrinkController {

    public void consultarNombre(String nombre) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + nombre);
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            if (responseDrinks.getDrinks() !=null){
                for (Drink item: responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cocktails con ese nombre");
            }


        } catch (MalformedURLException e) {
            System.out.println("La URL es invalida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }
    public void consultarLetra(String letra) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letra);
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            if (responseDrinks.getDrinks() !=null){
                for (Drink item: responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cocktails con ese nombre");
            }


        } catch (MalformedURLException e) {
            System.out.println("La URL es invalida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }

    public void consultarLetra(String palabra, String busqueda) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?"+busqueda+"="+palabra);
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            if (responseDrinks.getDrinks() !=null){
                for (Drink item: responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cocktails con ese nombre");
            }


        } catch (MalformedURLException e) {
            System.out.println("La URL es invalida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }

    public void obtenerAleatorio(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            System.out.println(responseDrinks.getDrinks().get(0));
        } catch (MalformedURLException e) {
            System.out.println("Mal escrita");
        } catch (StreamReadException e) {
            System.out.println("Fallo en la lecturo");
        } catch (DatabindException e) {
            System.out.println("Fallo en la asociacion");
        } catch (IOException e) {
            System.out.println("Fallo en internet");
        }
    }

    public void guardarFavorito(){
        ObjectMapper mapper = new ObjectMapper();

    }
}
