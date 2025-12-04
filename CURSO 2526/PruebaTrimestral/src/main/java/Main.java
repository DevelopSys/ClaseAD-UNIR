import controller.FileController;
import controller.ProductoController;
import dao.ProductoDAO1;
import dao.ProductoDAOImp;
import model.Producto;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        FileController fileController = new FileController();
        ArrayList<Producto> listado = new ArrayList<>();
        listado.add(new Producto(1, 10.0, "Producto1", "Categoria1"));
        listado.add(new Producto(2, 20.0, "Producto2", "Categoria2"));
        listado.add(new Producto(3, 30.0, "Producto3", "Categoria3"));
        listado.add(new Producto(4, 40.0, "Producto4", "Categoria4"));
        listado.add(new Producto(5, 50.0, "Producto5", "Categoria5"));
        // fileController.exportarFichero(listado);
        // fileController.importarProductos();
        fileController.trasnformacionHTML();
        ProductoController productoController = new ProductoController();
        productoController.insertarProductos();
        productoController.actualizarProductos("perifericos");

    }
}
