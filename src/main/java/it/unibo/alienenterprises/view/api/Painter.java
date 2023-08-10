package it.unibo.alienenterprises.view.api;

import it.unibo.alienenterprises.controller.renderers.Renderable;
import it.unibo.alienenterprises.controller.renderers.Renderer;

/**
 * Interface responsible for rendering the {@link GameWorld}.
 * 
 * @author Giulia Bonifazi
 */
public interface Painter extends Renderable {
    /**
     * Add one or more renderers to the {@link Painter}.
     * 
     * @param renderers the renderer/s to be added
     */
    void addAll(Renderer... renderers);

}
