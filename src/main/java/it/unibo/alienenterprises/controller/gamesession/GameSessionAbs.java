package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.World;

/**
 * Generic GameSession.
 */
public abstract class GameSessionAbs implements GameSession {
    private static final int MAX_INPUT = 5;
    protected final World world;
    protected final String playerID;
    private GameLoopThread gameLoop;

    /**
     * Create an instance of the GameSessionAbs class.
     * 
     * @param world    the {@link GameWorld}
     * @param playerID the id of the chosen player class.
     */
    public GameSessionAbs(final World world, String playerID) {
        this.world = world;
        this.playerID = playerID;
    }

    @Override
    public final GameLoop getGameLoop() {
        return this.gameLoop;
    }

    @Override
    public void gameOver() {
        this.gameLoop.stopLoop();
    }

    @Override
    public InputQueue startSession(RendererManager rendererManager) {
        InputQueue queue = new InputQueue(MAX_INPUT);
        this.gameLoop = new GameLoopThread(queue, rendererManager, this.world, this.playerID);
        this.gameLoop.start();
        return queue;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

}
