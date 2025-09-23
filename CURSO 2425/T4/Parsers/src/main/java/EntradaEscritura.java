import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

public class EntradaEscritura
{
    public static void main(String[] args) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.newDocument(); // preparado para escribir el XML

            // Element -> cada uno de los datos, nodos o valores que estan en el XML

            Element elementRoot = document.createElement("videoclub");
            document.appendChild(elementRoot);

            Element elementChild = document.createElement("pelicula");
            elementChild.setAttribute("titulo","Titulo de la pelicula");
            elementChild.setAttribute("categoria","Accion");
            elementChild.setAttribute("valoracion","5.3");
            elementRoot.appendChild(elementChild);

            Element elementChildInner = document.createElement("sinopsis");
            elementChildInner.setTextContent("Esta es la sinopsis de la pelicula de este nodo");
            elementChild.appendChild(elementChildInner);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domFile = new DOMSource(document);
            StreamResult escritura = new StreamResult(new File("videoclub.xml"));
            transformer.transform(domFile, escritura);





        } catch (ParserConfigurationException e) {
            System.out.println("Error en la creacion del document bulder");
        } catch (TransformerConfigurationException e) {
            System.out.println("Error en la creacion del transformer");
        } catch (TransformerException e) {
            System.out.println("error en la transformacion de los datos");
        }
    }
}
