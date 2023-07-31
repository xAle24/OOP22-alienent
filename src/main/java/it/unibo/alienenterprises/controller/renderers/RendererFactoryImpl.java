package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.GiuliaShip;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.Ship;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;
import it.unibo.alienenterprises.model.impl.components.GiuliaHitboxComponentCircle;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Implementation of the {@link RendererFactory}.
 */
public class RendererFactoryImpl implements RendererFactory {
    private final CanvasPainter canvasPaint;

    public RendererFactoryImpl(CanvasPainter canvasPaint) {
        this.canvasPaint = canvasPaint;
    }

    @Override
    public Renderer createGameObjectRenderer(GameObject obj) {
        return new RendererAbs(obj, this.canvasPaint) {

            @Override
            protected Image findImage() {
                return new Image(getClass().getResourceAsStream("/sprites_test/cat-g5485248ce_640.png"),
                        50.0, 50.0, false, false);
            }

        };
    }

    // @Override
    // public Renderer createGiuliaShipRenderer(GiuliaShip ship) {
    // return new Renderer() {
    // };
    // }
}
