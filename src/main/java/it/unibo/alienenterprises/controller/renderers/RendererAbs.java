package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.sprites.Sprite;

/**
 * Abstract {@link Renderer} implementation.
 * 
 * @author Giulia Bonifazi
 */
public abstract class RendererAbs implements Renderer {
    private final GameObject obj;
    private Sprite sprite;

    /**
     * RendererAbs constructor.
     * 
     * @param obj   the {@link GameObject} to be rendered
     * @param objID the game object's id
     */
    public RendererAbs(final GameObject obj, final String objID) {
        this.obj = obj;
        this.sprite = new Sprite(objID, this.obj.getPosition(), this.obj.getVelocity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.sprite.render(this.obj.getPosition(), this.obj.getVelocity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShown() {
        return this.obj.isAlive();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}
