package model;

import lombok.Data;

import java.util.List;
@Data
public class RespuestaProductos {
    private List<Product> products;
    private int total;
    private int skip;
    private int limit;
}
