package it.unibo.alienenterprises.model.geometry;

/**
 * represent a Line in a cartesian plane in the form ax+by+c=0
 */
public class Line2D {

    private final double a;
    private final double b;
    private final double c;

    /**
     * @param a
     * @param b
     * @param c
     */
    public Line2D(double a, double b, double c) {
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
     * @return
     */
    public double getA() {
        return a;
    }

    /**
     * @return
     */
    public double getB() {
        return b;
    }

    /**
     * @return
     */
    public double getC() {
        return c;
    }

    @Override
    public String toString() {
        return getA() + "x" + (getB() > 0 ? "+" : "") + getB() + "y" + (getC() > 0 ? "+" : "") + getC();
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Line2D other = (Line2D) obj;
        if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a)) {
            return false;
        }
        if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b)) {
            return false;
        }
        if (Double.doubleToLongBits(c) != Double.doubleToLongBits(other.c)) {
            return false;
        }
        return true;
    }

}
