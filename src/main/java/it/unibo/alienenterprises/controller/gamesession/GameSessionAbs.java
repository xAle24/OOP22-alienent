package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.gameloop.GameLoopThread;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.world.World;

/**
 * Generic GameSession.
 * 
 * @author Giulia Bonifazi
 */
public abstract class GameSessionAbs implements GameSession {
    private static final int MAX_INPUT = 5;
    private final String playerID;
    private final UserAccount account;
    private final World world;
    private GameObject player;

    private GameLoopThread gameLoop;

    /**
     * {@inheritDoc}
     * Create an instance of the GameSessionAbs class.
     * 
     * @param world    the {@link GameWorld}
     * @param playerID the id of the chosen player class.
     */
    public GameSessionAbs(final World world, final UserAccount acc, final String playerID) {
        this.world = world;
        this.playerID = playerID;
        this.account = acc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.gameLoop.pauseLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        this.gameLoop.resumeLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOver() {
        return this.world.isOver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        this.gameLoop.stopLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputQueue startSession(final RendererManager rendererManager) {
        InputQueue queue = new InputQueue(MAX_INPUT);
        this.gameLoop = new GameLoopThread(queue, rendererManager, this.world, this.playerID, this.account);
        this.player = this.world.getPlayer();
        this.gameLoop.start();
        return queue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayerHealth() {
        var health = this.player.gethealth();
        if (health <= 0) {
            return 0;
        } else {
            return health;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.world.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAccount getUserAccount() {
        return this.account;
    }

}
