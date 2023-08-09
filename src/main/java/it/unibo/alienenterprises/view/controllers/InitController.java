package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import javafx.scene.Scene;

/**
 * Controller interface; with this, all controllers tied to a view can access
 * the main {@link Controller} and the current scene.
 * 
 * @author Giulia Bonifazi
 */
public interface InitController {

    /**
     * Initialize the controller.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param scene      the current {@link Scene}.
     */
    void init(Controller controller, Scene scene);
}
