package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.bounds.Dimensions;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import it.unibo.alienenterprises.view.ViewType;

/**
 * Main controller of the game.
 * 
 * @author Giulia Bonifazi
 */
public interface Controller {

    /**
     * Initiate a {@link GameSession}.
     * 
     * @param playerID the ID of the chosen player ship.
     */
    void initiateGameSession(String playerID);

    /**
     * Changes the scene that is being displayed.
     * 
     * @param type the type of scene that has to be displayed
     */
    void changeScene(ViewType type);

    /**
     * Return the {@link UserAccountHandler} that is being used.
     * 
     * @return the account handler.
     */
    UserAccountHandler getUserAccountHandler();

    /**
     * Gets current {@link GameSession}.
     * 
     * @return the current game session.
     */
    GameSession getGameSession();

    /**
     * Return the {@link UserAccount} currently logged in.
     * 
     * @return the user account
     */
    UserAccount getUserAccount();

    /**
     * Gets the Dimensions that have to be considered when creating the gameworld.
     * 
     * @return the dimensions
     */
    Dimensions getArenaDimensions();

    /**
     * Sets the {@link UserAccount} instance in the Controller after logging in or
     * signing up.
     * 
     * @param account the newly logged in account
     */
    void setUserAccount(UserAccount account);

    /**
     * Save the game.
     */
    void save();
}
