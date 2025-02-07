package org.example.gestorligas.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gestorligas.LigasApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // tantas variables como elementos graficos quiera controlar
    @FXML
    private Button btnLogin, btnVaciar;

    @FXML
    private TextField editCorreo, editPass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        acciones();

    }

    private void acciones() {
        btnLogin.setOnAction(event -> {
            if (editCorreo.getText().isEmpty() || editPass.getText().isEmpty()) {
                // aviso
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos");
                alert.setContentText("Faltan datos por rellenar");
                alert.showAndWait();
            } else {
                // login -> base de datos -> HIBERNATE
                if (editCorreo.getText().equalsIgnoreCase("borja@gmail.com")
                        && editPass.getText().equals("1234")) {
                    // cargar el FXML
                    FXMLLoader fxmlLoader = new FXMLLoader(LigasApp.class.getResource("main-view.fxml"));
                    try {
                        Parent root = fxmlLoader.load();
                        // scene
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Ventana principal");
                        stage.show();

                        ((Stage)(btnVaciar.getScene().getWindow())).close();

                    } catch (IOException e) {
                        System.out.println("Fallo en la carga del XML");
                    }
                    // stage
                    // show
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Datos");
                    alert.setContentText("Fallo de credenciales");
                    alert.showAndWait();
                }
            }
        });
        btnVaciar.setOnAction(event -> {
            editCorreo.clear();
            editPass.clear();
        });
    }
}
