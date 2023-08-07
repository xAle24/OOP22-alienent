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
    public Renderer createGameObjectRenderer(GameObject obj, String objID) {
        return new RendererAbs(obj, objID) {

        };
    }

    // @Override
    // public Renderer createGiuliaShipRenderer(GiuliaShip ship) {
    // return new Renderer() {
    // };
    // }
}
