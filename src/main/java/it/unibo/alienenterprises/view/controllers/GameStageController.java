package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class GameStageController implements InitController {
    @FXML
    private StackPane canvasContainer;
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;

    private Controller controller;

    @Override
    public void init(Controller controller) {
        this.controller = controller;
    }

    @FXML
    public void handleQuit() {
        this.controller.getGameSession().gameOver();
        this.controller.changeScene(ViewType.GAMEOVER);
    }

}
