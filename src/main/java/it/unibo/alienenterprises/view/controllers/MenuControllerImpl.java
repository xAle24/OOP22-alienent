package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
/**
 * Implementations controller of the view menu.
 */
public class MenuControllerImpl implements MenuController, InitController {
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
    @Override @FXML
    public void playPressed() {
        this.controller.changeScene(ViewType.SHIPSELECT);
    }
    /**
     * @inheritDoc
     */
    @Override @FXML
    public void shopPressed() {
        this.controller.changeScene(ViewType.SHOP);
    }
    /**
     * @inheritDoc
     */
    @Override @FXML
    public void tutorialPressed() {
        this.controller.changeScene(ViewType.TUTORIAL);
    }
}
