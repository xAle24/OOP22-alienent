package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.Dimensions;
import it.unibo.alienenterprises.model.geometry.Point2D;

public class WorldDimensions implements Dimensions {
    private double width;
    private double height;

    public WorldDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public WorldDimensions(Point2D dimensions) {
        this.width = dimensions.getX();
        this.height = dimensions.getY();
    }

    @Override
    public Point2D getBounds() {
        return new Point2D(width, height);
    }

}
