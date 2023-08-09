package it.unibo.alienenterprises.view.controllers;

import java.util.Optional;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.view.ViewType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
/**
 * Controls the login view.
 */
public class LoginControllerImpl implements LoginController, InitController {
    private static final int ALERTDURATION = 6; 
    private Controller controller; 
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label alert;
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
    @FXML
    @Override
    public void handleLogin() {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            popUp("field");
        } else {
            Optional<UserAccount> account = controller.getUserAccountHandler().login(usernameField.getText(), 
                passwordField.getText());
            if (!account.isEmpty()) {
                controller.setUserAccount(account.get());
                controller.changeScene(ViewType.MAINMENU);
            } else {
                popUp("incorrect");
                passwordField.clear();
            }
        }
    }
    private void popUp(final String cod) {
        if (cod.equals("field")) {
            alert.setText("Missing credentials");
        } else if (cod.equals("incorrect")) {
            alert.setText("Incorrect credentials");
        }
        alert.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(ALERTDURATION));
        delay.setOnFinished(e -> alert.setVisible(false));
        delay.play();
    }
    /**
     * @inheritDoc
     */
    @FXML
    @Override
    public void handleRegister() {
        controller.changeScene(ViewType.REGISTRATION);
    }
}
