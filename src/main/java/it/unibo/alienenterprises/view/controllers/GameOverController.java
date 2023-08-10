package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controls the game over screen.
 * 
 * @author Giulia Bonifazi
 */
public final class GameOverController implements InitController {
    @FXML
    private Label scoreDisplay;
    @FXML
    private Button mainmenuButton;
    @FXML
    private Button quitButton;

    private Controller controller;

    @Override
    public void init(final Controller controller, final Scene scene) {
        this.controller = controller;
        this.controller.save();
        this.scoreDisplay.setText(Integer.toString(this.controller.getGameSession().getScore()));
    }

    /**
     * Handle when the main menu button is clicked.
     */
    @FXML
    public void onMainMenu() {
        this.controller.changeScene(ViewType.MAINMENU);
    }
}
