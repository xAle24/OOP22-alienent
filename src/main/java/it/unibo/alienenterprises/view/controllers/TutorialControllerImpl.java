package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.Scene;

/**
 * Implementation of TutorialController.
 * 
 * @author Ginevra Bartolini
 */
public class TutorialControllerImpl implements TutorialController, InitController {
    private Controller controller;

    /**
     * @inheritDoc
     */
    @Override
    public void init(final Controller controller, final Scene scene) {
        this.controller = controller;
    }

    /**
     * @inheritDoc
     */
    @Override
    @FXML
    public void exitPressed() {
        this.controller.changeScene(ViewType.MAINMENU);
    }

}
