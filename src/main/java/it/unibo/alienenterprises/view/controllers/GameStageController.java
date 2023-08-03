package it.unibo.alienenterprises.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.alienenterprises.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameStageController implements InitController {
    @FXML
    private BorderPane canvasContainer;
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    private Controller controller;

    public void start(Controller controller) {
        this.controller = controller;
    }

    private void handleQuit() {
        this.controller.getGameSession().gameOver();
    };

}
