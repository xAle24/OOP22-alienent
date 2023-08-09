package it.unibo.alienenterprises.controller.bounds;

import it.unibo.alienenterprises.model.geometry.Point2D;
import javafx.stage.Screen;

/**
 * Class that contains the dimensions for the game arena. This application uses
 * the preset.
 * 
 * @author Giulia Bonifazi
 */
public class ArenaDimensions implements Dimensions {
    private final double WIDTH = Screen.getPrimary().getBounds().getWidth();
    private final double HEIGHT = 600.0;
    private double width;
    private double height;

    public ArenaDimensions(Point2D dimensions) {
        this.width = dimensions.getX();
        this.height = dimensions.getY();
    }

    public ArenaDimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public ArenaDimensions() {
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

}
