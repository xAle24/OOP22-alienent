package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class RegistrationControllerImpl implements RegistrationController, InitController{
private Controller controller; 
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label Alert;
    @Override
    public void init(Controller controller) {
        this.controller = controller;
    }
    @Override @FXML
    public void handleRegister() {    
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            popUp("field");
        } else {
            this.controller.getUserAccountHandler().registration(usernameField.getText(), passwordField.getText());
            this.controller.changeScene(ViewType.LOGIN);
        }
    }
    private void popUp(String cod){
        if (cod.equals("field")) {
            Alert.setText("Missing credentials");
        } else if (cod.equals("incorrect")) {
            Alert.setText("Incorrect credentials");
        }
        Alert.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        delay.setOnFinished(e -> Alert.setVisible(false));
        delay.play();
    }
}
