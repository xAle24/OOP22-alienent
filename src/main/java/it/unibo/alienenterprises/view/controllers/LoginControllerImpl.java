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
    private static final String FIELD = "field";
    private static final String INCORRECT = "incorrect";
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
            final Optional<UserAccount> account = controller.getUserAccountHandler().login(usernameField.getText(), 
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
        if (FIELD.equals(cod)) {
            alert.setText("Missing credentials");
        } else if (INCORRECT.equals(cod)) {
            alert.setText("Incorrect credentials");
        }
        alert.setVisible(true);
        final PauseTransition delay = new PauseTransition(Duration.seconds(ALERTDURATION));
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
