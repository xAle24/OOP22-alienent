package it.unibo.alienenterprises.controller.api;

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
}
