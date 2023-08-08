package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import javafx.scene.Scene;

/**
 * Interface implemented by every controller of a {@link Scene}.
 */
public interface InitController {

    /**
     * Initializes the controller when it is created.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param scene      the current scene.
     */
    void init(final Controller controller, final Scene scene);
}
