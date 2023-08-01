package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.view.sprites.Sprite;

/**
 * Renderer interface. The Renderer will be responsible for communicating the
 * appropriate informations to the View, so it can render every
 * {@link GameObject} properly.
 */
public interface Renderer {

    /**
     * This method will be called every frame and will update the image on screen.
     * Iit pulls information from the {@link Gameobject} and transfers it to
     * the View.
     */
    void render();

    /**
     * Returns the isAlive value from the {@link GameObject} contained in the
     * Renderer.
     * 
     * @return false if the object is dead, true if it is not.
     */
    boolean isShown();

    /**
     * Gets the {@link Sprite} from the Renderer.
     */
    Sprite getSprite();
}
