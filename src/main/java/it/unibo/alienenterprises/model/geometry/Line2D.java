package it.unibo.alienenterprises.model.geometry;

/**
 * represent a Line in a cartesian plane in the form ax+by+c=0.
 */
public class Line2D {

    private final double a;
    private final double b;
    private final double c;

    /**
     * A constructor that creates a line from the given components.
     * To have a geometrical meaning at least one of a and b must be != 0.
     * 
     * @param a
     * @param b
     * @param c
     */
    public Line2D(final double a, final double b, final double c) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("ERROR: the components (0,0," + c + ") doesn't represent a Line");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * @param p
     * @return the distance between the line and the given point.
     */
    public double distancePoint(final Point2D p) {
        final double num = Math.abs(a * p.getX() + b * p.getY() + c);
        final double den = Math.sqrt(a * a + b * b);
        return num / den;
    }

    /**
     * @param p1
     * @param p2
     * @return return the line passing through the given points.
     */
    public static Line2D fromTwoPoints(final Point2D p1, final Point2D p2) {
        return new Line2D(p2.getY() - p1.getY(), p1.getX() - p2.getX(), p1.getY() * p2.getX() - p1.getX() * p2.getY());
    }

    /**
     * @return the value that multiply x
     */
    public double getA() {
        return a;
    }

    /**
     * @return the value that multiply y
     */
    public double getB() {
        return b;
    }

    /**
     * @return the known value
     */
    public double getC() {
        return c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getA() + "x" + (getB() > 0 ? "+" : "") + getB() + "y" + (getC() > 0 ? "+" : "") + getC();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
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
        final Line2D other = (Line2D) obj;
        if (this.getB() == 0 && other.getB() == 0) {
            if (this.getC() / this.getA() == other.getC() / other.getA()) {
                return true;
            }
            return false;
        }
        return this.getA() / this.getB() == other.getA() / other.getB()
                && this.getC() / this.getB() == other.getC() / other.getB();
    }

}
