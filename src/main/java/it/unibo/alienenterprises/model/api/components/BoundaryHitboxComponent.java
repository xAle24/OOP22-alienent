package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.geometry.Line2D;
/**
 * Iterface for boundar hitbox.
 */
public interface BoundaryHitboxComponent extends HitboxComponent {
    /**
     * Identify the position of the boundary.
     */
    public enum Locations{
        /**
         * Identify upper boundary.
         */
        UP,
        /**
         * Identify right buondary.
         */
        RIGHT,
        /**
         * Identify lower boundary.
         */
        DOWN,
        /**
         * Identify left boundary.
         */
        LEFT;
    }
    /**
     * Set the location of the object.
     * @param location where set the object.
     */
    void setLocations(Locations location);
    /**
     * Returns the line.
     * @return Line2D of the hitbox boundary component.
     */
    Line2D getLine();
}
