package it.unibo.alienenterprises.model.geometry;

public class Vector2D {

    private final Angle angle;
    private final double module;

    public Vector2D(final int angle, final double module) {
        this.angle = new Angle(angle);
        this.module = module;
    }

    public int getAngle() {
        return angle.getValue();
    }

    public double getModule() {
        return module;
    }

    public double getXComponent(){
        return Math.cos(this.getAngle())*this.getModule();
    }

    public double getYComponent(){
        return Math.sin(this.getAngle())*this.getModule();
    }

    public Point2D traslate(final Point2D p){
        return new Point2D(p.getX()+this.getXComponent(), p.getY()+this.getYComponent());
    }
    
}
