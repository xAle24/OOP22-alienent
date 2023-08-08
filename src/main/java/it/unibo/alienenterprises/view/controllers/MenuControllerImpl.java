package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;

public class MenuControllerImpl implements MenuController, InitController{
    private Controller controller; 
    @Override
    public void init(Controller controller) {
        this.controller = controller;
    }

    @Override @FXML
    public void playPressed() {
        this.controller.changeScene(ViewType.SHIPSELECT);
    }
    @Override @FXML
    public void shopPressed() {
        this.controller.changeScene(ViewType.SHOP);
    }
    @Override @FXML
    public void tutorialPressed() {
        this.controller.changeScene(ViewType.TUTORIAL);
    }
}
