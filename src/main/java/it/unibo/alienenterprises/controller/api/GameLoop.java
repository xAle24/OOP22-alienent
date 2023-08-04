package it.unibo.alienenterprises.controller.api;

import it.unibo.alienenterprises.view.renderers.RendererManager;

/**
 * Starts when a new game session is started, updates the game periodically.
 */
public interface GameLoop {

    /**
     * Updates the gameworld.
     * 
     * @param deltaTime time elapsed since last update.
     */
    void updateGame(double deltaTime);

    /**
     * Temporarily pause the gameloop.
     */
    void pauseLoop();

    /**
     * Resume the gameloop after it has been paused.
     */
    void resumeLoop();

    /**
     * Permanently stop the gameloop.
     */
    void stopLoop();
}
