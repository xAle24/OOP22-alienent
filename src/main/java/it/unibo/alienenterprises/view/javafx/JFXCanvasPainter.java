package it.unibo.alienenterprises.view.javafx;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.renderers.Renderer;
import it.unibo.alienenterprises.view.api.Painter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * This class will draw each {@link Sprite} on the {@link Canvas} it contains.
 * 
 * @author Giulia Bonifazi
 */
public final class JFXCanvasPainter implements Painter {
    private static final double DEGREES_TURN = 90.0;
    private GraphicsContext gc;
    private Canvas canvas;
    private Set<Renderer> renderers;
    private Set<Renderer> toBeRemoved;

    /**
     * CanvasPainter constructor.
     * 
     * @param canvas the {@link Canvas} on which this class draws.
     */
    public JFXCanvasPainter(final Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.renderers = new HashSet<>();
        this.toBeRemoved = new HashSet<>();
    }

    @Override
    public void render() {
        this.clearCanvas();
        this.renderers.forEach(r -> {
            if (r.isShown()) {
                r.render();
                ImageView image = r.getSprite().getImageView();
                drawRotatedImage(image.getImage(), image.getRotate() + DEGREES_TURN, image.getX(), image.getY());
            } else {
                this.toBeRemoved.add(r);
            }
        });
        this.toBeRemoved.forEach(r -> this.renderers.remove(r));
        this.toBeRemoved.clear();
    }

    @Override
    public void addAll(final Renderer... renderers) {
        this.renderers.addAll(Set.of(renderers));
    }

    /**
     * Clears the canvas of every {@link Sprite} drawn on it, in preparation of a
     * new render.
     */
    private void clearCanvas() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    /**
     * Creates a new {@link Rotate} for the {@link Image} and communicates it to the
     * {@link GraphicsContext}.
     * 
     * @param angle the angle of the rotation
     * @param px    the X coordinate of the pivot point
     * @param py    the Y coordinate of the pivot point
     */
    private void rotate(final double angle, final double px, final double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Finds the pivot of the {@link Image} and rotates it.
     * 
     * @param image    the image to be rotated
     * @param angle    the angle by which the image must be rotated
     * @param topLeftX the top left X coordinate of the image
     * @param topLeftY the top left Y coordinate of the image
     */
    private void drawRotatedImage(final Image image, final double angle, final double topLeftX, final double topLeftY) {
        gc.save();
        rotate(angle, topLeftX + image.getRequestedWidth() / 2, topLeftY + image.getRequestedHeight() / 2);
        gc.drawImage(image, topLeftX, topLeftY);
        gc.restore();
    }

}
