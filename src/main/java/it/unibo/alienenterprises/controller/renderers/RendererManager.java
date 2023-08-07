package it.unibo.alienenterprises.controller.renderers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;

public class RendererManager {
    private List<Renderer> renderers;
    private final CanvasPainter canvasPaint;
    private final RendererFactory renderFactory;

    public RendererManager(final CanvasPainter cp) {
        this.canvasPaint = cp;
        this.renderers = new ArrayList<>();
        this.renderFactory = new RendererFactoryImpl();
    }

    public void addRenderer(GameObject obj, String objID) {
        var newRenderer = this.renderFactory.createGameObjectRenderer(obj, objID);
        this.renderers.add(newRenderer);
        this.canvasPaint.addAll(newRenderer);
    }

    public void render() {
        this.canvasPaint.render();
    }
}
