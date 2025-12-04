package controller;

import model.Producto;
import model.ProductoLista;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileController {

    public void exportarFichero(ArrayList<Producto> lista) {

        File file = new File("src/main/java/controller/productos.dat");
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error entrada salida");
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    public void importarProductos() {
        File file = new File("src/main/java/controller/productos.dat");
        ObjectInputStream ois;

        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Producto> lista = (ArrayList<Producto>) ois.readObject();
            ProductoLista productoLista = new ProductoLista();
            productoLista.setLista(lista);
            JAXBContext context = JAXBContext.newInstance(ProductoLista.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(productoLista, new File("src/main/java/controller/productos.xml"));


        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println("Error al leer la clase");
        } catch (JAXBException e) {
            System.out.println("Clase no reconocida");
        }
    }

    public void trasnformacionHTML() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("src/main/java/controller/listado.xslt"));
            Transformer transformer = null;

            transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File("src/main/java/controller/productos.xml"));
            Result resultado = new StreamResult(new File("src/main/java/controller/productos.html"));
            transformer.transform(xml, resultado);
        } catch (TransformerException e) {
            System.out.println("Error en la transformacion del fichero");
        }
    }

    public List<Producto> getListaProductosXML(){
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ProductoLista.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductoLista productoLista = (ProductoLista) unmarshaller.unmarshal(new File("src/main/java/controller/productos.xml"));
            return productoLista.getLista();
        } catch (JAXBException e) {
            System.out.println("Error en la traduccion");
        }

        return null;

    }
}
