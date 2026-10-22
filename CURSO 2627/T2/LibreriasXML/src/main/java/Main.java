import controller.FileController;
import model.Usuario;

public class Main {

    public static void main(String[] args) {
        FileController fileController = new FileController();
        // fileController.escritorXML("alumnos.xml");
        // fileController.lectorXML("alumnos.xml");
        fileController.transformarXML("alumnos.xml");
    }
}
