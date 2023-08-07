package it.unibo.alienenterprises.model.api;

import it.unibo.alienenterprises.model.geometry.Point2D;

/**
 * Class that contains dimensions for a rectangular arena.
 */
public interface Dimensions {

    /**
     * Returns a {@link Point2D} in which the x coordinate is the width, and the y
     * coordinate is the height.
     * 
     * @return
     */
    Point2D getBounds();
}
