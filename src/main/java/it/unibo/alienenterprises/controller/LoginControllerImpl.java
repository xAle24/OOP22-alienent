package it.unibo.alienenterprises.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import it.unibo.alienenterprises.controller.api.LoginController;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
/**
 * Controls the login view.
 */
public class LoginControllerImpl implements LoginController, Initializable {
    @FXML
    private TextField usernameField;
    private TextField passwordField;
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @Override
    public void handleLogin() {
        UserAccountHandler userAccount = new UserAccountHandlerImpl();
        Optional<UserAccountImpl> account = 
            userAccount.login(usernameField.getAccessibleText(),passwordField.getAccessibleText());
        if (account.isPresent()) {
            //TODO
        } else {
            //messaggio errore pass
        }
    }
}
