package it.unibo.alienenterprises.view.api;

import it.unibo.alienenterprises.controller.renderers.Renderer;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.sprites.Sprite;

public interface Painter {

    /**
     * Use each {@link Renderer} to draw its {@link Sprite} on the gamestage.
     */
    void render();

    /**
     * Add one or more renderers to the {@link Painter}.
     * 
     * @param renderers the renderer/s to be added
     */
    void addAll(Renderer... renderers);

}
