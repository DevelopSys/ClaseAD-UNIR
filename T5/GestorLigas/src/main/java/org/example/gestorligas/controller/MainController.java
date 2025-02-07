package org.example.gestorligas.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnVolver;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            btnVolver.setOnAction(event -> {
                System.exit(0);
            });
    }
}
