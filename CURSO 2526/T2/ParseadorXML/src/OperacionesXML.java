import model.Actor;
import model.Pelicula;
import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OperacionesXML {

    public void lecturaXML() {
        // leer el fichero XML
        // Flujo de texto plano
        // una linea txt -> string -> split(,)
        File file = new File("usuarios.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            // document.getDocumentByTag("input") -> NodeList
            // document.querySelector()
            NodeList nodosUsuario = document.getElementsByTagName("usuario");
            for (int i = 0; i < nodosUsuario.getLength(); i++) {
                System.out.println("Nodo analizando");
                Element nodo = (Element) nodosUsuario.item(i);
                String nacionalidad = nodo.getAttribute("nacionalidad");
                int edad = Integer.parseInt(nodo.getAttribute("edad"));
                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent();
                String apellido = nodo.getElementsByTagName("apellido").item(0).getTextContent();
                Usuario usuario = new Usuario(nombre, apellido, nacionalidad, edad);
                System.out.println("La nacionalidad es: " + nacionalidad);
                System.out.println("El nombre es: " + nombre);
                System.out.println("La apellido es: " + apellido);
                System.out.println("La edad es: " + edad);
            }


        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        } catch (SAXException e) {
            System.out.println("Error de SAX");
        }
    }

    public void escritorXML() {
        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
        Pelicula p1 = new Pelicula(
                "Inception",
                "Christopher Nolan",
                new Actor[]{new Actor("Leonardo DiCaprio"), new Actor("Joseph Gordon-Levitt"), new Actor("Elliot Page")},
                "Ciencia ficción / Acción",
                2010,
                8.8
        );

        Pelicula p2 = new Pelicula(
                "The Dark Knight",
                "Christopher Nolan",
                new Actor[]{new Actor("Christian Bale"), new Actor("Heath Ledger"), new Actor("Gary Oldman")},
                "Acción / Crimen / Drama",
                2008,
                9.0
        );

        Pelicula p3 = new Pelicula(
                "Forrest Gump",
                "Robert Zemeckis",
                new Actor[]{new Actor("Tom Hanks"), new Actor("Robin Wright"), new Actor("Gary Sinise")},
                "Drama / Romance",
                1994,
                8.8
        );

        Pelicula p4 = new Pelicula(
                "The Shawshank Redemption",
                "Frank Darabont",
                new Actor[]{new Actor("Tim Robbins"), new Actor("Morgan Freeman"), new Actor("Bob Gunton")},
                "Drama",
                1994,
                9.3
        );

        Pelicula p5 = new Pelicula(
                "The Godfather",
                "Francis Ford Coppola",
                new Actor[]{new Actor("Marlon Brando"), new Actor("Al Pacino"), new Actor("James Caan")},
                "Crimen / Drama",
                1972,
                9.2
        );

        Pelicula p6 = new Pelicula(
                "Gladiator",
                "Ridley Scott",
                new Actor[]{new Actor("Russell Crowe"), new Actor("Joaquin Phoenix"), new Actor("Connie Nielsen")},
                "Acción / Aventura / Drama",
                2000,
                8.5
        );

        Pelicula p7 = new Pelicula(
                "Interstellar",
                "Christopher Nolan",
                new Actor[]{new Actor("Matthew McConaughey"), new Actor("Anne Hathaway"), new Actor("Jessica Chastain")},
                "Ciencia ficción / Drama",
                2014,
                8.6
        );

        Pelicula p8 = new Pelicula(
                "Pulp Fiction",
                "Quentin Tarantino",
                new Actor[]{new Actor("John Travolta"), new Actor("Samuel L. Jackson"), new Actor("Uma Thurman")},
                "Crimen / Drama",
                1994,
                8.9
        );

        Pelicula p9 = new Pelicula(
                "The Matrix",
                "Lana Wachowski, Lilly Wachowski",
                new Actor[]{new Actor("Keanu Reeves"), new Actor("Carrie-Anne Moss"), new Actor("Laurence Fishburne")},
                "Ciencia ficción / Acción",
                1999,
                8.7
        );
// Cartel: https://m.media-amazon.com/images/I/51EG732BV3L._AC_.jpg

        Pelicula p10 = new Pelicula(
                "Parasite",
                "Bong Joon-ho",
                new Actor[]{new Actor("Song Kang-ho"), new Actor("Cho Yeo-jeong"), new Actor("Choi Woo-shik")},
                "Drama / Thriller",
                2019,
                8.6
        );

        listaPeliculas.add(p1);
        listaPeliculas.add(p2);
        listaPeliculas.add(p3);
        listaPeliculas.add(p4);
        listaPeliculas.add(p5);
        listaPeliculas.add(p6);
        listaPeliculas.add(p7);
        listaPeliculas.add(p8);
        listaPeliculas.add(p9);
        listaPeliculas.add(p10);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // document.createElement("")
            // document
            //<cartelera id=1>
            Element nodoRoot = document.createElement("cartelera");
            nodoRoot.setAttribute("id", "1");
            document.appendChild(nodoRoot);

            // foreach -> recorrer -> no para modificar
            for (Pelicula item : listaPeliculas) {


                item.parseoElemento(document,item,nodoRoot);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource domSource = new DOMSource(document);
                StreamResult result = new StreamResult(new File("peliculas.xml"));
                transformer.transform(domSource,result);
            }

            // node
            // node
            // node
            // node
            // node
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error en el parseo del documento");
        }


    }
}
