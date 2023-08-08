package it.unibo.alienenterprises.controller.renderers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.api.Painter;

public class RendererManager {
    private List<Renderer> renderers;
    private final Painter painter;
    private final RendererFactory renderFactory;

    public RendererManager(final Painter painter) {
        this.painter = painter;
        this.renderers = new ArrayList<>();
        this.renderFactory = new RendererFactoryImpl();
    }

    public void addRenderer(GameObject obj, String objID) {
        var newRenderer = this.renderFactory.createGameObjectRenderer(obj, objID);
        this.renderers.add(newRenderer);
        this.painter.addAll(newRenderer);
    }

    public void render() {
        this.painter.render();
    }
}
