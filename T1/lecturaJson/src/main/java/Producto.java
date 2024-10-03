public class Producto {

    private String title, description;
    private int id, stock;
    private double price;

    public Producto() {
    }

    public Producto(String title, String description, int id, int stock, double price) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.stock = stock;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
