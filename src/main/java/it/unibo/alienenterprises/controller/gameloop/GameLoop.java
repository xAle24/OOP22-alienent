package it.unibo.alienenterprises.controller.gameloop;

/**
 * Starts when a new {@link GameSession} is started, updates the game
 * periodically.
 * 
 * @author Giulia Bonifazi
 */
public interface GameLoop {

    /**
     * Temporarily pauses the gameloop.
     */
    void pauseLoop();

    /**
     * Resumes the gameloop after it has been paused.
     */
    void resumeLoop();

    /**
     * Stops the gameloop.
     */
    void stopLoop();
}
