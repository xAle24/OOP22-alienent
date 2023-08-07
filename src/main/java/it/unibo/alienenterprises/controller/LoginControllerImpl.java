package it.unibo.alienenterprises.controller;

import java.util.Optional;
import it.unibo.alienenterprises.controller.api.LoginController;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Controls the login view.
 */
public class LoginControllerImpl implements LoginController,InitController {
    private Controller controller; 
    @FXML
    private TextField usernameField;
    private TextField passwordField;
    private Label Alert;
    @Override
    public void init(Controller controller) {
        this.controller = controller;
    }
    @Override
    public void handleLogin() {
        Optional<UserAccountImpl> account = 
            controller.getUserAccountHandler().login(usernameField.getAccessibleText(), passwordField.getAccessibleText());
        if (account.isPresent()) {
            controller.changeScene(ViewType.MAINMENU);
            //TODO
        } else {
            Alert.setText("Account non trovato, riprovare");
            usernameField.clear();
            passwordField.clear();
        }
    }
}
