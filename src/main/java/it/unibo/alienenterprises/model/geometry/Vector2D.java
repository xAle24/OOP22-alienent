package it.unibo.alienenterprises.model.geometry;

/**
 * Describe the functionalities of a Vector.
 */
public final class Vector2D {

    /**
     * A vector with angle and module equals 0
     */
    public static final Vector2D NULL_VECTOR = new Vector2D(0, 0);

    private final double module;
    private final double angle;

    private Vector2D(final double angle, final double module) {
        this.angle = confineAngle(angle);
        this.module = module;
    }

    /**
     * @param angle  in degrees
     * @param module
     * @return The vector with that angle and module
     */
    public static Vector2D fromAngleAndModule(final double angle, final double module) {
        return new Vector2D(angle, module);
    }

    public static Vector2D fromComponents(final double xComp, final double yComp) {
        final double angle;
        final double module = Math.sqrt(xComp * xComp + yComp * yComp);
        if (xComp == 0) {
            if (yComp == 0) {
                angle = 0;
            } else {
                angle = yComp < 0 ? 270 : 90;
            }
        } else {
            final var aTan = Math.toDegrees(Math.atan(yComp / xComp));
            angle = xComp < 0 ? aTan + 180 : aTan;
        }
        return new Vector2D(angle, module);
    }

    /**
     * @param a starting point
     * @param b ending point
     * @return the vector that goes from a and b
     */
    public static Vector2D fromTwoPoints(final Point2D a, final Point2D b) {
        return fromComponents(b.getX() - a.getX(), b.getY() - a.getY());
    }

    /**
     * @param a
     * @param b
     * @param module the module of the resulting vector
     * @return the vector with the direction from a to b and the given module
     */
    public static Vector2D fromTwoPointsAndModule(final Point2D a, final Point2D b, final double module) {
        var vet = fromTwoPoints(a, b);
        return fromAngleAndModule(vet.getAngle(), module);
    }

    /**
     * @return xComponent
     */
    public double getxComp() {
        return this.module * Math.cos(Math.toRadians(this.angle));
    }

    /**
     * @return yComponent
     */
    public double getyComp() {
        return this.module * Math.sin(Math.toRadians(this.angle));
    }

    /**
     * @return vector angle in degrees
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * @return vector module
     */
    public double getModule() {
        return this.module;
    }

    /**
     * @param p
     * @return the point resulting form the translation
     */
    public Point2D translate(final Point2D p) {
        return new Point2D(p.getX() + this.getxComp(), p.getY() + this.getyComp());
    }

    /**
     * @param vec
     * @return return the vector obtainded adding vec
     */
    public Vector2D add(final Vector2D vec) {
        return fromComponents(this.getxComp() + vec.getxComp(), this.getyComp() + vec.getyComp());
    }

    /**
     * Multipy the vector for the given number
     * 
     * @param a
     * @return A new vector equal to the multiplication
     */
    public Vector2D mul(final double a) {
        return fromAngleAndModule(this.angle, this.module * a);
    }

    private double confineAngle(final double angle) {
        double newAngle = angle;
        while (newAngle >= 360) {
            newAngle = newAngle - 360;
        }
        while (newAngle <= -360) {
            newAngle = newAngle + 360;
        }
        if (newAngle < 0) {
            newAngle = 360 - newAngle;
        }
        return newAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Vector2D [module=" + this.module + ", angle=" + this.angle + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(module);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(angle);
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
        if (Double.doubleToLongBits(module) != Double.doubleToLongBits(other.module)) {
            return false;
        }
        if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle)) {
            return false;
        }
        return true;
    }

}
