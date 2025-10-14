package model;

import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pelicula {

    private String titulo, director;
    private Actor[] actores;
    private String genero;
    private int anio;
    private double calificacion;

    public Element parseoElemento(Document document, Pelicula item, Element nodoRoot){
        Element nodoPelicula = document.createElement("pelicula");
                /*if (item.getGenero() != null) {
                }*/
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
}
