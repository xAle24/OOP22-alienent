package it.unibo.alienenterprises.controller.renderers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.api.Painter;

public class RendererManager {
    private List<Renderer> renderers;
    private final Painter painter;

    public RendererManager(final Painter painter) {
        this.painter = painter;
        this.renderers = new ArrayList<>();
    }

    public void addRenderer(GameObject obj, String objID) {
        var newRenderer = new SimpleRenderer(obj, objID);
        this.renderers.add(newRenderer);
        this.painter.addAll(newRenderer);
    }

    public void render() {
        this.painter.render();
    }
}
