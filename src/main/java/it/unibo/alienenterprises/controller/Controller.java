package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import it.unibo.alienenterprises.view.ViewType;

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
     * Changes the scene that is being displayed.
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
     * Sets the {@link UserAccount} instance in the Controller after logging in or
     * signing up.
     * 
     * @param account
     */
    void setUserAccount(UserAccount account);

    /**
     * Save the game.
     */
    void save();
}
