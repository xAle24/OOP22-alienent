package it.unibo.alienenterprises.controller.api;

/* Starts when a new game session is started, updates the game periodically. */
public interface GameLoop {

    /* Periodically updates the gameworld */
    void update();

    /* Temporarily pause the gameloop */
    void pause();

    /* Resume the gameloop after it has been paused */
    void resume();

    /* Stop the gameloop */
    void stop();
}
