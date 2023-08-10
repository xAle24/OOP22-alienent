package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.renderers.RendererManager;

/**
 * Whenever a "new game" is pressed on the main menu, a new instance of this
 * class will be started.
 * 
 * @author Giulia Bonifazi
 */
public interface GameSession {

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Handle the game over.
     */
    void gameOver();

    /**
     * Start session after receiving the {@link RendererManager}.
     * 
     * @param rendererManager
     */
    InputQueue startSession(RendererManager rendererManager);

    /**
     * Get the player's health to display.
     * 
     * @return the player's health
     */
    int getPlayerHealth();

    /**
     * Get the current score to display.
     * 
     * @return the score.
     */
    int getScore();

    /**
     * Return if the session is over.
     * 
     * @return whether the session is over.
     */
    boolean isOver();
}
