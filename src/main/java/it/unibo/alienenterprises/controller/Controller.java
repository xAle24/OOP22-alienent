package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.api.SceneController;
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
    GameSession getGameSession();

    /**
     * Initiate a {@link GameSession}.
     * 
     * @param multiplayer true if the session is multiplayer, false otherwise.
     */
    void initiateGameSession(boolean multiplayer);

    /**
     * Returns the {@link SceneController}.
     * 
     * @return the scene controller.
     */
    SceneController getSceneController();
}
