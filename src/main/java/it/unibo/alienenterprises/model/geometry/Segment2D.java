package it.unibo.alienenterprises.model.geometry;

/**
 * represent a segment in a cartesian plane
 */
public class Segment2D {

    private final Point2D a;
    private final Point2D b;

    /**
     * @param a first point
     * @param b second point
     */
    public Segment2D(final Point2D a, final Point2D b) {
        this.a = a;
        this.b = b;
    }

    /**
     * @return
     */
    public Point2D getA() {
        return a;
    }

    /**
     * @return
     */
    public Point2D getB() {
        return b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((a == null) ? 0 : a.hashCode());
        result = prime * result + ((b == null) ? 0 : b.hashCode());
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
        Segment2D other = (Segment2D) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a))
            return false;
        if (b == null) {
            if (other.b != null)
                return false;
        } else if (!b.equals(other.b))
            return false;
        return true;
    }

}
