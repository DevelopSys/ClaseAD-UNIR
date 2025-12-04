package dao;

import model.Producto;

public interface ProductoDAO {

    public void addProducto(Producto producto);
    public void borrarProducto(int id);
    public void actualizarProductos(String categoria);
}
