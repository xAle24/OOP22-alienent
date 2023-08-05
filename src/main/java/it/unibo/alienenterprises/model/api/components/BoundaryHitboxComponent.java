package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.geometry.Line2D;

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

    void setLocations(Locations location);

    Line2D getLine();
}
