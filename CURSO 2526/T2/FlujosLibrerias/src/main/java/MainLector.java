import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MainLector {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Listado.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Listado listado = (Listado) unmarshaller.unmarshal(new File("usuarios.xml"));
            System.out.println(listado.getListado().size());
            for (Usuario item: listado.getListado()) {
                item.mostrarDatos();
            }
        } catch (JAXBException e) {
            System.out.println("Mapeado fallido");
        }
    }
}
