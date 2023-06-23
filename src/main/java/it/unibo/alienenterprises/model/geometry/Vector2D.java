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

}
