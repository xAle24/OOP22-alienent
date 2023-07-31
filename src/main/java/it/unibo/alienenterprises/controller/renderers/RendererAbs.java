package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.sprites.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Abstract {@link Renderer} implementation.
 */
public abstract class RendererAbs implements Renderer {
    private final GameObject obj;
    private Sprite sprite;

    /**
     * RendererAbs constructor.
     * 
     * @param obj the {@link GameObject} to be rendered
     */
    public RendererAbs(GameObject obj, CanvasPainter canvasPaint) {
        this.obj = obj;
        this.sprite = new Sprite(findImage(), this.obj.getPosition(), this.obj.getVelocity());
        canvasPaint.addAll(this);
    }

    @Override
    public void render() {
        this.sprite.render(this.obj.getPosition(), this.obj.getVelocity());
    }

    @Override
    public boolean isShown() {
        return this.obj.isAlive();
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    protected Image findImage() {
        return new Image(getClass().getResourceAsStream("resources/sprites_test/cat-g5485248ce_640.png"),
                50.0, 50.0, false, false);
    }
}
