package it.unibo.alienenterprises.controller.renderers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.api.Painter;

/**
 * Manager for all the {@link Renderable} objects of the game.
 * 
 * @author Giulia Bonifazi
 */
public class RendererManager implements Renderable {
    private final Painter painter;
    private final Renderable viewRenderable;
    private List<Renderer> renderers;

    /**
     * Creates a new instance of this class.
     * 
     * @param painter        the painter that draws the renderable objects
     * @param viewRenderable the view that shows what is happening during the game.
     */
    public RendererManager(final Painter painter, final Renderable viewRenderable) {
        this.painter = painter;
        this.viewRenderable = viewRenderable;
        this.renderers = new ArrayList<>();
    }

    /**
     * Adds one {@link GameObject} to the list by creating its {@link Renderer}.
     * 
     * @param obj   the object
     * @param objID the object's ID, necessary for the renderer creation.
     */
    public void addRenderer(final GameObject obj, final String objID) {
        var newRenderer = new SimpleRenderer(obj, objID);
        this.renderers.add(newRenderer);
        this.painter.addAll(newRenderer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.viewRenderable.render();
        this.painter.render();
        this.viewRenderable.render();
    }
}
