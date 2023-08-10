package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
/**
 * Implementations of controller for view registration.
 */
public class RegistrationControllerImpl implements RegistrationController, InitController {
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
    @Override @FXML
    public void handleRegister() {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            popUp("field");
        } else {
            this.controller.getUserAccountHandler().registration(usernameField.getText(), passwordField.getText());
            this.controller.changeScene(ViewType.LOGIN);
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
}
