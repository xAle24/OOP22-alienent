package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.api.GameLoop;

/**
 * Whenever a "new game" is pressed on the main menu, a new instance of this
 * class will be started.
 */
public interface GameSession {

    /**
     * Get the current match's score.
     * 
     * @return the current match's score.
     */
    int getScore();

    /**
     * Get the current session's GameLoop.
     * 
     * @return the current session's GameLoop.
     */
    GameLoop getGameLoop();

    /**
     * Handle the game over.
     */
    void gameOver();
}
