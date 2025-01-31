import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Entrada {

    public static void main(String[] args) {
        // gestor
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // lector
        try {
            DocumentBuilder lector = builderFactory.newDocumentBuilder();
            Document document = lector.parse(new File("src/main/java/peliculas.xml"));
            Node nodoRaiz = document.getDocumentElement();
            NodeList lista = nodoRaiz.getChildNodes();
            for (int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    System.out.println("nodo");
                    Node nodoTitulo = node.getAttributes().getNamedItem("titulo");
                    Node nodoPuntuacion = node.getAttributes().getNamedItem("puntuacion");
                    if (Double.parseDouble(nodoPuntuacion.getTextContent())>9){
                        System.out.println(nodoTitulo.getTextContent() + " "+nodoPuntuacion.getTextContent());
                    }

                    // System.out.println();
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el la creacion del document builder");
        } catch (IOException | SAXException e) {
            System.out.println("Error en el parseo del fichero");
        }


    }
}
