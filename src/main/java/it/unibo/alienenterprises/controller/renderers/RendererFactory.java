package it.unibo.alienenterprises.controller.renderers;

import it.unibo.alienenterprises.model.GiuliaShip;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.Ship;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

/**
 * Factory that creates the renderers to be used by the view.
 */
public interface RendererFactory {

    public Renderer createGameObjectRenderer(GameObject obj);

    // /**
    // * Creates a {@link Ship} Renderer.
    // *
    // * @return the renderer
    // */
    // public Renderer createShipRenderer(Ship ship);

    // /**
    // * Creates a {@link Wall} Renderer.
    // *
    // * @return the renderer
    // */
    // public Renderer createWallRenderer(Wall wall);

    // /**
    // * Creates a {@link Projectile} Renderer.
    // *
    // * @return the renderer
    // */
    // public Renderer createProjectileRenderer(Projectile projectile);
}
