package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.view.sprites.Sprite;

/**
 * Renderer interface. The Renderer will be responsible for communicating the
 * appropriate informations to the View, so it can render every
 * {@link GameObject} properly.
 * 
 * @author Giulia Bonifazi
 */
public interface Renderer extends Renderable {

    /**
     * Returns the isAlive value from the {@link GameObject} contained in the
     * Renderer.
     * 
     * @return false if the object is dead, true if it is not.
     */
    boolean isShown();

    /**
     * Gets the {@link Sprite} from the Renderer.
     * 
     * @return the sprite
     */
    Sprite getSprite();
}
