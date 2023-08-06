package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.sprites.Sprite;
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
    public RendererAbs(GameObject obj) {
        this.obj = obj;
        this.sprite = new Sprite(findImage(), this.obj.getPosition(), this.obj.getVelocity());
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

    protected abstract Image findImage();
}
