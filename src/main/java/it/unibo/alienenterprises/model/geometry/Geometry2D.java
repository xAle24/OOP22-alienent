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

    /**
     * @param point
     * @param vector
     * @return
     */
    public static Point2D traslate(final Point2D point, final Vector2D vector){
        return new Point2D(point.x()+vector.getXComponent(), point.y()+vector.getYComponent());
    }

    public static double distance(final Point2D a, final Point2D b){
        return Math.sqrt(Math.pow(a.x()-b.x(), 2)+Math.pow(a.y()-b.y(), 2));
    }
}
