package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.World;

public class SimpleGameSession extends GameSessionAbs {
    private final UserAccount account;

    public SimpleGameSession(final World world, final RendererManager rendererManager, final UserAccount account) {
        super(world, rendererManager);
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
