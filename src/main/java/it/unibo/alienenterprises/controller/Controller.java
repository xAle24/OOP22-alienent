package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.gamesession.GameSession;

/**
 * Main controller of the game.
 */
public interface Controller {

    /**
     * Gets current {@link GameSession}.
     * 
     * @return the current game session.
     */
    public GameSession getGameSession();

    /**
     * Initiate a {@link GameSession}.
     * 
     * @param multiplayer true if the session is multiplayer, false otherwise.
     */
    public void initiateGameSession(boolean multiplayer);

    // public void notifyScore(int score);
}
