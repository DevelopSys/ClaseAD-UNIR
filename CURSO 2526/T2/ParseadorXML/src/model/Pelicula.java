package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Pelicula {

    private String titulo, director;
    private Actor[] actores;
    private String genero;
    private int anio;
    private double calificacion;

    public Pelicula(String titulo, String director, Actor[] actors, String genero, int anio, double calificacion) {
        this.titulo = titulo;
        this.director = director;
        this.actores = actors;
        this.genero = genero;
        this.anio = anio;
        this.calificacion = calificacion;
    }

    public Element parseoElemento(Document document, Pelicula item, Element nodoRoot) {
        Element nodoPelicula = document.createElement("pelicula");
        /*
         * if (item.getGenero() != null) {
         * }
         */
        nodoPelicula.setAttribute("genero", item.getGenero());
        nodoPelicula.setAttribute("anio", String.valueOf(item.getAnio()));
        nodoRoot.appendChild(nodoPelicula);

        Element nodoTitulo = document.createElement("titulo");
        nodoTitulo.setTextContent(item.getTitulo());

        Element nodoDirector = document.createElement("director");
        nodoDirector.setTextContent(item.getDirector());

        nodoPelicula.appendChild(nodoTitulo);
        nodoPelicula.appendChild(nodoDirector);
        Element nodoActores = document.createElement("actores");
        nodoPelicula.appendChild(nodoActores);
        for (Actor actor : item.getActores()) {
            Element nodoActor = document.createElement("actor");
            nodoActor.setTextContent(actor.getNombre());
            nodoActores.appendChild(nodoActor);
        }
        return nodoPelicula;
    }

    public Actor[] getActores() {
        return actores;
    }

    public int getAnio() {
        return anio;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public String getTitulo() {
        return titulo;
    }
}
