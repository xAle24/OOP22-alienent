package it.unibo.alienenterprises.model.api.components;

public interface BoundaryHitboxComponent {
    
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
}
