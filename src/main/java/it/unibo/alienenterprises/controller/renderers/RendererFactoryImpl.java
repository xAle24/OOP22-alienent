package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.api.GameObject;
import javafx.scene.image.Image;

/**
 * Implementation of the {@link RendererFactory}.
 */
public class RendererFactoryImpl implements RendererFactory {

    public RendererFactoryImpl() {
    }

    @Override
    public Renderer createGameObjectRenderer(GameObject obj) {
        return new RendererAbs(obj) {

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
