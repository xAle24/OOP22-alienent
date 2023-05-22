package it.unibo.alienenterprises.model.geometry;

/**
 * Geometry2D
 * 
 * Defines the geometricals rules of the game
 */
public class Geometry2D {

    /**
     * Point2D
     */
    public record Point2D(double x, double y) {
    }

    /**
     * Vector2D
     */
    public record Vector2D(double angle, double module) {
        public double getXComponent(){
            return Math.cos(this.angle())*this.module();
        }

        public double getYComponent(){
            return Math.sin(this.angle())*this.module();
        }
    }

    public record Circle2D(Point2D center, double ray){
    }

    /**
     * @param point
     * @param vector
     * @return
     */
    public static Point2D traslate(final Point2D point, final Vector2D vector){
        return new Point2D(point.x()+vector.getXComponent(), point.y()+vector.getYComponent());
    }

    /**
     * @param a
     * @param b
     * @return
     */
    public static double pointDistance(final Point2D a, final Point2D b){
        return Math.sqrt(Math.pow(a.x()-b.x(), 2)+Math.pow(a.y()-b.y(), 2));
    }

    /**
     * @param c1
     * @param c2
     * @return
     */
    public static boolean circleIntersection(final Circle2D c1, final Circle2D c2){
        return pointDistance(c1.center(), c2.center()) <= c1.ray()+c2.ray();
    }
}
