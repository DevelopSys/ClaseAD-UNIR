import controller.LigaController;
import model.Entrenador;
import model.Jugador;
import model.Liga;

public class Entrada {

    public static void main(String[] args) {
        LigaController ligaController = new LigaController();
        //ligaController.agregarEntrenador(new Entrenador("BorjaEntrenador",0,11));
        // ligaController.contratarEntrenador(1,1);
        /*ligaController.darAltaLiga(new Liga("BBVA"));
        ligaController.darAltaLiga(new Liga("Calcio"));
        ligaController.darAltaLiga(new Liga("Bundesliga"));
        ligaController.darAltaLiga(new Liga("Premier"));*/
        //ligaController.inscribirseLiga(1,4);
        // ligaController.crearJugador(new Jugador("Julian",10000,"Española"),4);
        //ligaController.contratarJugador(1,1);
        // ligaController.analizarPlantilla(1);
        ligaController.getEquiposCompeticion(3);
    }
}
