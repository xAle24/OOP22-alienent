package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.viewstates.ViewState;

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
     * @param viewState
     * 
     * @param scene
     */
    void setScene(ViewState viewState);
}
