package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.InputQueue;
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
    protected final UserAccount account;
    protected final World world;
    protected GameObject player;

    private GameLoopThread gameLoop;

    /**
     * {@inheritDoc}
     * Create an instance of the GameSessionAbs class.
     * 
     * @param world    the {@link GameWorld}
     * @param playerID the id of the chosen player class.
     */
    public GameSessionAbs(final World world, UserAccount acc, String playerID) {
        this.world = world;
        this.playerID = playerID;
        this.account = acc;
    }

    @Override
    public void pause() {
        this.gameLoop.pauseLoop();
    }

    @Override
    public void resume() {
        this.gameLoop.resumeLoop();
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
    public InputQueue startSession(RendererManager rendererManager) {
        InputQueue queue = new InputQueue(MAX_INPUT);
        this.gameLoop = new GameLoopThread(queue, rendererManager, this.world, this.playerID, this.account);
        this.player = this.world.getPlayer();
        this.gameLoop.start();
        return queue;
    }

}
