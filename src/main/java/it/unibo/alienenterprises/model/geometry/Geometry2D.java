package it.unibo.alienenterprises.model.geometry;

/**
 * Geometry2D
 * 
 * Defines the geometricals rules of the game
 */
public class Geometry2D {

    /**
     * Point2D
     */
    public record Point2D(double x, double y) {
    }

    /**
     * Vector2D
     */
    public record Vector2D(double angle, double module) {
    }

    //TODO
}
