package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.world.GameWorld;
import it.unibo.alienenterprises.model.world.World;

/**
 * Simple {@link GameSession} implementation.
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
        int score = this.world.getScore();
        this.account.setMoney(this.account.getMoney() + score);
        if (this.account.getHighscore() < score) {
            this.account.setHighscore(score);
        }
    }

    @Override
    public int getPlayerHealth() {
        var health = this.player.gethealth();
        if (health <= 0) {
            return 0;
        } else {
            return health;
        }
    }

    @Override
    public int getScore() {
        return this.world.getScore();
    }

    @Override
    public boolean isOver() {
        return this.world.isOver();
    }

}
