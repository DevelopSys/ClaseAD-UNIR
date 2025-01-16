import controller.LigaController;
import model.Entrenador;

public class Entrada {

    public static void main(String[] args) {
        LigaController ligaController = new LigaController();
        //ligaController.agregarEntrenador(new Entrenador("BorjaEntrenador",0,11));
        ligaController.obtenerEntrenador(1);
    }
}
