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
public class GameObjectRendererManager implements RendererManager<GameObject> {
    private final Painter painter;
    private final Renderable viewRenderable;
    private List<Renderer> renderers;

    /**
     * Creates a new instance of this class.
     * 
     * @param painter        the painter that draws the renderable objects
     * @param viewRenderable the view that shows what is happening during the game.
     */
    public GameObjectRendererManager(final Painter painter, final Renderable viewRenderable) {
        this.painter = painter;
        this.viewRenderable = viewRenderable;
        this.renderers = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRenderer(final GameObject obj) {
        var newRenderer = new SimpleRenderer(obj, obj.getId());
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
