package org.example.gestorligas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.example.gestorligas.dao.JugadorDAO;
import org.example.gestorligas.model.Jugador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Jugador> tabla;
    @FXML
    private TableColumn columnaNombre, columnaGoles, columnaNacionalidad, columnaValor;

    private ObservableList<Jugador> listaJugadores;

    @FXML
    private Button btnVolver;

    @FXML
    private MenuItem menuSalir, menuBorrar, menuAnadir, menuAutor;

    private JugadorDAO jugadorDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        instancias();
        acciones();

    }

    private void acciones() {
        menuBorrar.setOnAction(event -> {
            if (tabla.getSelectionModel().getSelectedIndex()!=-1){
                Jugador jugadorSeleccionado = (Jugador) tabla.getSelectionModel().getSelectedItem();
                //int indice = tabla.getSelectionModel().getSelectedIndex();
                listaJugadores.remove(jugadorSeleccionado);
                jugadorDAO.removeJugador(jugadorSeleccionado);
                tabla.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aviso");
                alert.setContentText("No hay seleccionado");
                alert.showAndWait();
            }

        });
        menuSalir.setOnAction(event -> {
            System.exit(0);
        });
        menuAutor.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El autor de la aplicación es Borja Martin");
            alert.setTitle("Ayuda");
            alert.showAndWait();
        });
        btnVolver.setOnAction(event -> {

            Jugador jugador= tabla.getSelectionModel().getSelectedItem();
            System.out.println(jugador.getId());
        });
    }

    private void instancias() {
        menuSalir.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_ANY));
        menuAutor.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_ANY));
        menuBorrar.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY));
        columnaGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaNacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        columnaValor.setCellValueFactory(new PropertyValueFactory<>("valorMercado"));
        jugadorDAO = new JugadorDAO();
        listaJugadores = FXCollections.observableArrayList();
        listaJugadores.addAll(jugadorDAO.getAllJugadores());
        tabla.setItems(listaJugadores);
    }
}
