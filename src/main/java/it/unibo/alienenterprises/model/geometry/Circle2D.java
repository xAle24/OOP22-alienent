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
     * @return true if the two cicle intersect with eachother, false otherwise
     */
    public boolean intersectWith(final Circle2D c) {
        return (this.getRay() + c.getRay()) > this.getCenter().distanceFrom(c.getCenter());
    }

    /**
     * @param l
     * @return true if the circle intersect the line, false otherwise
     */
    public boolean intersectWith(final Line2D l) {
        return this.getRay() > l.distancePoint(this.getCenter());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Circle2D [center=" + getCenter() + ", r=" + getRay() + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((center == null) ? 0 : center.hashCode());
        long temp;
        temp = Double.doubleToLongBits(r);
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
        final Circle2D other = (Circle2D) obj;
        if (center == null) {
            if (other.center != null) {
                return false;
            }
        } else if (!center.equals(other.center)) {
            return false;
        }
        return Double.doubleToLongBits(r) == Double.doubleToLongBits(other.r);
    }

}
