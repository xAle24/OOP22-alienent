package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;

/**
 * View interface.
 */
public interface View {
    /**
     * Initializes the View.
     * 
     * @param controller the main {@link Controller} of the game
     */
    void init(Controller controller);

    /**
     * Sets the scene that has to be shown.
     * 
     * @param scene
     */
    void setScene();
}
