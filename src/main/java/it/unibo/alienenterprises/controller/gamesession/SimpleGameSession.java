package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.World;

/**
 * Simple {@link GameSession} implementation.
 */
public class SimpleGameSession extends GameSessionAbs {
    private final UserAccount account;

    /**
     * Creates a new instance of this class.
     * 
     * @param world    the current {@link GameWorld}
     * @param account  the current {@link UserAccount}
     * @param playerID the ID of the chosen player class.
     */
    public SimpleGameSession(final World world, final UserAccount account,
            final String playerID) {
        super(world, playerID);
        this.account = account;
    }

    @Override
    public void gameOver() {
        super.gameOver();
        int score = this.world.getScore();
        this.account.setMoney(this.account.getMoney() + score);
        if (this.account.getHighscore() < score) {
            this.account.setHighscore(score);
        }
    }

}
