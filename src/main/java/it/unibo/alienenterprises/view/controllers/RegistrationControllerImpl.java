package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrationControllerImpl implements RegistrationController, InitController{
private Controller controller; 
    @FXML
    private TextField usernameField;
    private TextField passwordField;
    @Override
    public void init(Controller controller) {
        this.controller = controller;
    }
    @Override @FXML
    public void handleRegister() {
        this.controller.getUserAccountHandler().registration(usernameField.getText(), passwordField.getText());
        this.controller.changeScene(ViewType.LOGIN);
    }
}
