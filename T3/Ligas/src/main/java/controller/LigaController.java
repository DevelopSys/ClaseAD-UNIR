package controller;

import dao.EntrenadorDAO;
import dao.EquipoDAO;
import model.Entrenador;
import model.Equipo;

public class LigaController {
    private EntrenadorDAO entrenadorDAO;
    private EquipoDAO equipoDAO;
    public LigaController(){
        entrenadorDAO = new EntrenadorDAO();
        equipoDAO = new EquipoDAO();
    }

    public void agregarEntrenador(Entrenador entrenador){

        // este entrenador tiene el titulo valido
        // este entrenador es valida para el puesto
        // todo ok
        if (entrenador.getCalificacion()<10){
            System.out.println("No vale para este equipo");
        } else {
            entrenadorDAO.agregarEntrenador(entrenador);
        }

        // fallo en el entrenador

    }
    public void obtenerEntrenador(int id){
        Entrenador entrenador = entrenadorDAO.obtenerEntranador(id);
        System.out.println(entrenador.getNombre());
        if (entrenador.getTitulos()>4){
            // lo contrato
            Equipo equipo = equipoDAO.getEquipo(1);
            equipo.setEntrenador(entrenador);
            equipoDAO.actualizarEquipo(equipo);

        } else {
            // si la cantidad de valoracion no es menor que 10
        }
    }

}
