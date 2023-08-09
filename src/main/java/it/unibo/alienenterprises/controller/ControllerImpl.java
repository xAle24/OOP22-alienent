package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.bounds.Dimensions;
import it.unibo.alienenterprises.controller.bounds.ArenaDimensions;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.gamesession.SimpleGameSession;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import it.unibo.alienenterprises.model.world.GameWorld;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewType;

/**
 * Main {@link Controller} of the game.
 * 
 * @author Giulia Bonifazi
 */
public final class ControllerImpl implements Controller {
    private final UserAccountHandler accHandler;
    private final View view;
    private UserAccount account = null;

    private GameSession currGameSession;

    /**
     * Creates a new instance of this class.
     * 
     * @param view the main view of the game
     */
    public ControllerImpl(final View view) {
        this.accHandler = new UserAccountHandlerImpl();
        this.view = view;
    }

    @Override
    public GameSession getGameSession() {
        return this.currGameSession;
    }

    @Override
    public void initiateGameSession(String playerID) {
        Dimensions wd = new ArenaDimensions();
        this.currGameSession = new SimpleGameSession(new GameWorld(wd), account, playerID);
    }

    @Override
    public void changeScene(ViewType type) {
        this.view.setScene(type);
    }

    @Override
    public UserAccountHandler getUserAccountHandler() {
        return this.accHandler;
    }

    @Override
    public UserAccount getUserAccount() {
        return this.account;
    }

    @Override
    public void setUserAccount(UserAccount account) {
        this.account = account;
    }

    @Override
    public void save() {
        this.accHandler.save(this.account);
    }
}
