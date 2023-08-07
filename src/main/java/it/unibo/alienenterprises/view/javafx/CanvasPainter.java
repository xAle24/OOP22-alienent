package it.unibo.alienenterprises.view.javafx;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.renderers.Renderer;
import it.unibo.alienenterprises.view.sprites.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * This class will draw each {@link Sprite} on the {@link Canvas} it contains.
 */
public final class CanvasPainter {
    private GraphicsContext gc;
    private Canvas canvas;
    private Set<Renderer> renderers;
    private Set<Renderer> toBeRemoved;

    /**
     * CanvasPainter constructor.
     * 
     * @param maxHeight
     *                  the max height of the {@link Canvas}; will be set to not
     *                  resizable.
     * @param maxWidth
     *                  the max width of the {@link Canvas}; will be set to not
     *                  resizable.
     */
    public CanvasPainter(double maxHeight, double maxWidth) {
        this.canvas = new Canvas(maxWidth, maxHeight);
        this.gc = canvas.getGraphicsContext2D();
        this.renderers = new HashSet<>();
        this.toBeRemoved = new HashSet<>();
    }

    /**
     * Add one or more renderers to the {@link CanvasPainter}.
     * 
     * @param renderers the renderer/s to be added
     */
    public void addAll(Renderer... renderers) {
        this.renderers.addAll(Set.of(renderers));
    }

    /**
     * Get the {@link Canvas} that {@link CanvasPainter} is drawing on.
     * 
     * @return the canvas
     */
    public Canvas getCanvas() {
        return this.canvas;
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
    private void rotate(double angle, double px, double py) {
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
    private void drawRotatedImage(Image image, double angle, double topLeftX, double topLeftY) {
        gc.save();
        rotate(angle, topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2);
        gc.drawImage(image, topLeftX, topLeftY);
        gc.restore();
    }

    /**
     * Use each {@link Renderer} to draw its {@link Sprite} on the gamestage.
     */
    public void render() {
        this.clearCanvas();
        this.renderers.forEach(r -> {
            if (r.isShown()) {
                r.render();
                ImageView image = r.getSprite().getImageView();
                drawRotatedImage(image.getImage(), image.getRotate(), image.getX(), image.getY());
            } else {
                this.toBeRemoved.add(r);
            }
        });
        this.toBeRemoved.forEach(r -> this.renderers.remove(r));
        this.toBeRemoved.clear();
    }

}
