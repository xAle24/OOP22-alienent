package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.world.World;

/**
 * Simple {@link GameSession} implementation.
 * 
 * @author Giulia Bonifazi
 */
public class SimpleGameSession extends GameSessionAbs {

    /**
     * {@inheritDoc}
     * Creates a new instance of this class.
     * 
     * @param world    the current {@link GameWorld}
     * @param account  the current {@link UserAccount}
     * @param playerID the ID of the chosen player class.
     */
    public SimpleGameSession(final World world, final UserAccount account,
            final String playerID) {
        super(world, account, playerID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        super.gameOver();
        var acc = getUserAccount();
        int score = getScore();
        acc.setMoney(score);
        if (acc.getHighscore() < score) {
            acc.setHighscore(score);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerHealth() {
        return super.getPlayerHealth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return super.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return super.isOver();
    }

}
