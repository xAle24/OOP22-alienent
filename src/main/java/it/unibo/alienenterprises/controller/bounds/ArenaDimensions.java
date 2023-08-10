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
    private static final double SCREEN_WIDTH_PERCENT = 1.0;
    private static final double SCREEN_HEIGHT_PERCENT = 0.8;
    private double width;
    private double height;

    /**
     * Creates a new instance of this class.
     * 
     * @param width  the width of the arena
     * @param height the height of the arena
     */
    public ArenaDimensions(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new instance of this class.
     * 
     * @param dimensions the dimensions of the arena, where the x coordinate is the
     *                   width and the y coordinate is the height
     */
    public ArenaDimensions(final Point2D dimensions) {
        this(dimensions.getX(), dimensions.getY());
    }

    /**
     * Creates a new instance of this class by using the screen bounds.
     */
    public ArenaDimensions() {
        this.width = Screen.getPrimary().getBounds().getWidth() * SCREEN_WIDTH_PERCENT;
        this.height = Screen.getPrimary().getBounds().getHeight() * SCREEN_HEIGHT_PERCENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return this.height;
    }

}
