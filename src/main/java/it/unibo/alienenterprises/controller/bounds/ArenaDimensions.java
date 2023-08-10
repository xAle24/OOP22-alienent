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
    private final double preset_width;
    private final double preset_height = 600.0;
    private double width;
    private double height;

    public ArenaDimensions(double width, double height) {
        this.preset_width = 900.0;
        this.width = width;
        this.height = height;
    }

    public ArenaDimensions(Point2D dimensions) {
        this(dimensions.getX(), dimensions.getY());
    }

    public ArenaDimensions() {
        this.preset_width = Screen.getPrimary().getBounds().getWidth();
        this.width = this.preset_width;
        this.height = this.preset_height;
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
