package it.unibo.alienenterprises.model.geometry;

public class Circle2D {

    private final Point2D center;
    private final double r;


    public Circle2D(final Point2D center, final double r) {
        this.center = center;
        this.r = r;
    }


    public Point2D getCenter() {
        return center;
    }


    public double getR() {
        return r;
    }

    public boolean intersecateWhith(final Circle2D c){
        return this.getR()+c.getR() < this.center.distanceFrom(c.getCenter());
    }
    
}
