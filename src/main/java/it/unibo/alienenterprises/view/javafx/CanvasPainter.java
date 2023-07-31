package it.unibo.alienenterprises.view.javafx;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.renderers.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

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
     * Add one or more renderers to the CanvasPainter.
     * 
     * @param renderers the renderer/s to be added
     */
    public void addAll(Renderer... renderers) {
        this.renderers.addAll(Set.of(renderers));
    }

    /**
     * Get the {@link Canvas} that CanvasPainter is drawing on.
     * 
     * @return
     */
    public Canvas getCanvas() {
        return this.canvas;
    }

    public void clearCanvas() {
        this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    /**
     * Use each {@link Renderer} to draw its {@link Sprite} on the gamestage.
     */
    public void render() {
        this.renderers.forEach(r -> {
            if (r.isShown()) {
                r.render();
                ImageView image = r.getSprite().getImageView();
                this.gc.drawImage(image.getImage(), image.getX(), image.getY());
            } else {
                this.toBeRemoved.add(r);
            }
        });
        this.toBeRemoved.forEach(r -> this.renderers.remove(r));
        this.toBeRemoved.clear();
    }

}
