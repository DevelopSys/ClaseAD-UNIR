package model;

public enum Tipo {

    EXTERNO(1,"EXT","trabajador externo"),
    INDEFINIDO(2,"IND","trabajador indefinido"),
    BECARIO (3, "BEC","trabajador becario");

    private int id;
    private String siglas, descripcion;

    Tipo(int id, String siglas, String descripcion){
        this.descripcion = descripcion;
        this.siglas = siglas;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
