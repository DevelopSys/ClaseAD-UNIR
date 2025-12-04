package controller;

import dao.ProductoDAO;
import dao.ProductoDAO1;
import dao.ProductoDAOImp;
import model.Producto;
import model.ProductoLista;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ProductoController {

    private ProductoDAOImp productoDAOImp;
    private FileController fileController;

    public ProductoController() {
        productoDAOImp = new ProductoDAOImp();
        fileController = new FileController();
    }

    public void insertarProductos() {
        // le pongo la capa de logica de la inserccion de los productos
        for (Producto item : fileController.getListaProductosXML()) {
            productoDAOImp.addProducto(item);
        }
    }

    public void actualizarProductos(String categoria){
        // cuales han sido los producotos mas vendidos en el ultimo mes
        productoDAOImp.actualizarProductos(categoria);
    }

    public void obtenerProductos(){
        productoDAOImp.getAllProductos();
        ProductoDAO1 productoDAO1 = new ProductoDAO1();

    }
}
