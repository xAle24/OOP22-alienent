package it.unibo.alienenterprises.controller.api;

import it.unibo.alienenterprises.model.api.InputSupplier.Input;

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
     * Stop the gameloop.
     */
    void stopLoop();

    /**
     * Convert the parameter to a {@link Input} and add it to the input queue.
     * 
     * @param string the key that was pressed.
     */
    void addInput(String string);
}
