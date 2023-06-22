package it.unibo.alienenterprises.model.geometry;

public class Point2D {

    private final double x;
    private final double y;

    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceFrom(final Point2D p){
        return Math.sqrt(Math.pow(this.getX()-p.getX(), 2)+Math.pow(this.getY()-p.getY(), 2));
    }
    
}
