package it.unibo.alienenterprises.view.controllers;

import java.util.Optional;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.view.ViewType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
/**
 * Controls the login view.
 */
public class LoginControllerImpl implements LoginController,InitController {
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
    @FXML
    @Override
    public void handleLogin() {
        Optional<UserAccountImpl> account = 
            controller.getUserAccountHandler().login(usernameField.getAccessibleText(), passwordField.getAccessibleText());
        if (!account.isEmpty()) {
            controller.changeScene(ViewType.MAINMENU);
            //TODO
        } else {
            Alert.setVisible(true);
            PauseTransition delay = new PauseTransition(Duration.seconds(6));
            delay.setOnFinished(e -> Alert.setVisible(false));
            delay.play();
            usernameField.clear();
            passwordField.clear();
        }
    }
    @FXML
    @Override
    public void handleRegister() {
        controller.changeScene(ViewType.REGISTRATION);
    }
}
