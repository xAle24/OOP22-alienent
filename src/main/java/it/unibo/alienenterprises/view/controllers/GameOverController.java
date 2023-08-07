package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverController implements InitController {
    @FXML
    private Label scoreDisplay;
    @FXML
    private Button mainmenuButton;
    @FXML
    private Button quitButton;

    private Controller controller;

    @Override
    public void init(Controller controller) {
        this.controller = controller;
        this.controller.save();
    }

    private void onMainMenu() {
        this.controller.changeScene(ViewType.MAINMENU);
    }
}
