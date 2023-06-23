package it.unibo.alienenterprises.model.geometry;

/**
 * Describe a circle in a 2D space.
 */
public class Circle2D {

    private final Point2D center;
    private final double r;

    /**
     * @param center
     * @param r
     */
    public Circle2D(final Point2D center, final double r) {
        this.center = center;
        this.r = r;
    }

    /**
     * @return the center
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * @return the ray of the circle
     */
    public double getRay() {
        return r;
    }

    /**
     * @param c
     * @return true if the two cicle are intersecated, false otherwise
     */
    public boolean intersecateWhith(final Circle2D c) {
        return this.getRay() + c.getRay() < this.center.distanceFrom(c.getCenter());
    }

}
