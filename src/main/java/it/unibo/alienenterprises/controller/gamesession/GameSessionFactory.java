package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;

/**
 * {@link GameSession} factory.
 */
public interface GameSessionFactory {

    /**
     * Builds a single player gamesession.
     * 
     * @return a single player game session.
     */
    public GameSession singleplayerGameSession();

    /**
     * Builds a multiplayer game session.
     * 
     * @return a multiplayer game session.
     */
    public GameSession multiplayerGameSession();
}
