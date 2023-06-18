package it.unibo.alienenterprises.model.api;

/* 
 * Whenever a "new game" is pressed on the main menu, a new instance of this class will be started.
 */
public interface GameSession {

    /*
     * Get the current match's score.
     */
    public int getScore();

    /*
     * Pause the game.
     */
    public void pauseLoop();

    /*
     * Resume the game after it has been paused.
     */
    public void resumeGame();

    /*
     * Handle the game over.
     */
    public void gameOver();
}
