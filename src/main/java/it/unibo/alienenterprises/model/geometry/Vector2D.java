package it.unibo.alienenterprises.model.geometry;

/**
 * Describe the functionalities of a Vector.
 */
public class Vector2D {

    private final Angle angle;
    private final double module;

    /**
     * @param angle
     * @param module
     */
    public Vector2D(final int angle, final double module) {
        this.angle = new Angle(angle);
        this.module = module;
    }

    public static Vector2D getFromComponents(final int x, final int y) {
        final double module = Math.sqrt(x * x + y * y);
        final int angle = (int) Math.acos(x / module);
        return new Vector2D(angle, module);
    }

    /**
     * @return the angle value
     */
    public int getAngle() {
        return angle.getValue();
    }

    /**
     * @return module of the vector
     */
    public double getModule() {
        return module;
    }

    /**
     * @return the x component of the vector
     */
    public double getXComponent() {
        return Math.cos(this.getAngle()) * this.getModule();
    }

    /**
     * @return the y component of the vector
     */
    public double getYComponent() {
        return Math.sin(this.getAngle()) * this.getModule();
    }

    /**
     * @param p a Point2D
     * @return a new point derived from the translation of p
     */
    public Point2D translate(final Point2D p) {
        return new Point2D(p.getX() + this.getXComponent(), p.getY() + this.getYComponent());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((angle == null) ? 0 : angle.hashCode());
        long temp;
        temp = Double.doubleToLongBits(module);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2D other = (Vector2D) obj;
        if (angle == null) {
            if (other.angle != null)
                return false;
        } else if (!angle.equals(other.angle))
            return false;
        if (Double.doubleToLongBits(module) != Double.doubleToLongBits(other.module))
            return false;
        return true;
    }

}
