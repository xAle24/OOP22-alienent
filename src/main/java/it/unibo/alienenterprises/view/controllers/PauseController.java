package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseController implements InitController {
    @FXML
    private Button quitButton;
    @FXML
    private Button continueButton;

    private Controller controller;

    @Override
    public void init(Controller controller) {
        this.controller = controller;
        this.quitButton.setOnAction(e -> this.onQuit());
        this.continueButton.setOnAction(e -> this.onContinue());
    }

    private void onContinue() {
        this.controller.changeScene(ViewType.GAMESTAGE);
        this.controller.getGameSession().getGameLoop().resumeLoop();
    }

    private void onQuit() {
        this.controller.changeScene(ViewType.GAMEOVER);
    }
}
