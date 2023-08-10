package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;

/**
 * View interface.
 * 
 * @author Giulia Bonifazi
 */
public interface View {
    /**
     * Predef width.
     */
    double MAX_WIDTH = 1920.0;
    /**
     * Predef height.
     */
    double MAX_HEIGHT = 1080.0;

    /**
     * Initializes the View.
     * 
     * @param controller the main {@link Controller} of the game
     */
    void init(Controller controller);

    /**
     * Sets the scene that has to be shown.
     * 
     * @param type the type of scene to be set
     */
    void setScene(ViewType type);

    /**
     * Returns the {@link Stage} width as the x coordinate and the height as the y
     * coordinate of a {@link Point2D} instance.
     * 
     * @return the stage width and height
     */
    Point2D getWidthHeight();
}
