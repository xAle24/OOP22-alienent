package it.unibo.alienenterprises.controller.renderers;

/**
 * Makes all the objects that implement it renderable.
 * 
 * @author Giulia Bonifazi
 */
public interface Renderable {

    /**
     * Does whatever is necessary to the object that implements this to render
     * itself or the objects contained within it.
     */
    void render();
}
