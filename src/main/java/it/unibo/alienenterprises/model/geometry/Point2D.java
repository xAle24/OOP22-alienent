package it.unibo.alienenterprises.model.geometry;

/**
 * Describe the functionalities of a point in a 2D space.
 */
public class Point2D {

    /**
     * The origin point: a point with x and y equals to 0.
     */
    public static final Point2D ORIGIN = new Point2D(0, 0);

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Point2D [x=" + getX() + ", y=" + getY() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2D other = (Point2D) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
                && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }

}
