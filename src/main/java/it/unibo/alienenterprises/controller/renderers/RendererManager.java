package it.unibo.alienenterprises.controller.renderers;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.model.GiuliaShip;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;

public class RendererManager {
    private List<Renderer> renderers;
    private final CanvasPainter canvasPaint;
    private final RendererFactory renderFactory;

    public RendererManager(final CanvasPainter cp) {
        this.canvasPaint = cp;
        this.renderers = new ArrayList<>();
        this.renderFactory = new RendererFactoryImpl(this.canvasPaint);
    }

    public void addRenderer(GameObject obj) {
        this.renderers.add(this.renderFactory.createGameObjectRenderer(obj));
    }

    public void render() {
        this.canvasPaint.clearCanvas();
        this.canvasPaint.render();
    }
}
