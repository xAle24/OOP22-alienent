package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.view.viewstates.ViewState;

/**
 * View interface.
 */
public interface View {
    public static final double MAX_WIDTH = 1920.0;
    public static final double MAX_HEIGHT = 1080.0;

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

    /**
     * Returns the {@link Stage} width as the x coordinate and the height as the y
     * coordinate of a {@link Point2D} instance
     * 
     * @return
     */
    Point2D getWidthHeight();
}
