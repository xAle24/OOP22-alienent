package it.unibo.alienenterprises.model.geometry;

/**
 * Describe the functionalities of a point in a 2D space.
 */
public class Point2D {

    private final double x;
    private final double y;

    /**
     * @param x
     * @param y
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x value
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y value
     */
    public double getY() {
        return y;
    }

    /**
     * @param p another point
     * @return the distance between the points
     */
    public double distanceFrom(final Point2D p) {
        return Math.sqrt(Math.pow(this.getX() - p.getX(), 2) + Math.pow(this.getY() - p.getY(), 2));
    }

}
