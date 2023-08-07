package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.api.SceneController;

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
     * @param playerID the ID of the chosen player ship.
     */
    void initiateGameSession(String playerID);

    /**
     * Returns the {@link SceneController}.
     * 
     * @return the scene controller.
     */
    SceneController getSceneController();

    /**
     * Changes the {@link Scene} that is being displayed.
     */
    void changeScene(ViewType type);

    /**
     * Return the {@link UserAccountHandler} that is being used.
     * 
     * @return the account handler.
     */
    UserAccountHandler getUserAccountHandler();

    /**
     * Return the {@link UserAccount} currently logged in.
     * 
     * @return
     */
    UserAccount getUserAccount();

    /**
     * Save the game.
     */
    void save();
}
