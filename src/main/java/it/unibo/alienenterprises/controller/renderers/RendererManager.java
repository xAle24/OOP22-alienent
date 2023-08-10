package it.unibo.alienenterprises.controller.renderers;

/**
 * Manages the renderers of the game.
 * 
 * @param <T> the selected class
 * 
 * @author Giulia Bonifazi
 */
public interface RendererManager<T> extends Renderable {

    /**
     * Adds one object the list by creating its {@link Renderer}.
     * 
     * @param obj the object to be rendered
     */
    void addRenderer(T obj);
}
