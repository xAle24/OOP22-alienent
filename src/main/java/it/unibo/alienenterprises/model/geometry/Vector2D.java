package it.unibo.alienenterprises.model.geometry;

/**
 * Describe the functionalities of a Vector.
 */
public class Vector2D {

    public static final Vector2D NULL_VECTOR = new Vector2D(0, 0);

    private final double xComp;
    private final double yComp;

    /**
     * @param xComponent
     * @param yComponent
     */
    public Vector2D(final double xComponent, final double yComponent) {
        this.xComp = xComponent;
        this.yComp = yComponent;
    }

    /**
     * @param angle  in degrees
     * @param module
     * @return The vecttor with that angle and module
     */
    public static Vector2D fromAngleAndModule(final double angle, final double module) {
        var r = Math.toRadians(angle);
        return new Vector2D(module * Math.cos(r), module * Math.sin(r));
    }

    /**
     * @return xComponent
     */
    public double getxComp() {
        return xComp;
    }

    /**
     * @return yComponent
     */
    public double getyComp() {
        return yComp;
    }

    /**
     * @return vector angle in degrees
     */
    public double getAngle() {
        if (xComp == 0) {
            if (yComp == 0) {
                return 0;
            }
            return yComp < 0 ? 270 : 90;
        }
        final var aTan = Math.toDegrees(Math.atan(yComp / xComp));
        return xComp<0 ? aTan+180 : aTan;
    }

    /**
     * @return vector module
     */
    public double getModule() {
        return Math.sqrt(xComp * xComp + yComp * yComp);
    }

    /**
     * @param p
     * @return the point resulting form the translation
     */
    public Point2D translate(final Point2D p) {
        return new Point2D(p.getX() + xComp, p.getY() + yComp);
    }

    /**
     * @param vec
     * @return return the vector obtainded adding vec
     */
    public Vector2D add(final Vector2D vec) {
        return new Vector2D(xComp + vec.getxComp(), yComp + vec.getyComp());
    }

    /**
     * Multipy the vector for the given number
     * @param a
     * @return A new vector equal to the multiplication
     */
    public Vector2D mul(final double a) {
        return new Vector2D(this.xComp * a, this.yComp * a);
    }

    @Override
    public String toString() {
        return "Vector2D [xComp=" + getxComp() + ", yComp=" + getyComp() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(xComp);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yComp);
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
        Vector2D other = (Vector2D) obj;
        if (Double.doubleToLongBits(xComp) != Double.doubleToLongBits(other.xComp)) {
            return false;
        }
        if (Double.doubleToLongBits(yComp) != Double.doubleToLongBits(other.yComp)) {
            return false;
        }
        return true;
    }

}
