package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.World;

/**
 * Generic GameSession.
 */
public abstract class GameSessionAbs implements GameSession {
    private GameLoopThread gameLoop;
    protected final World world;

    /**
     * Create an instance of the GameSessionAbs class.
     * 
     * @param view
     * @param world
     */
    public GameSessionAbs(final World world, final RendererManager rendererManager, final String playerID) {
        this.world = world;
        this.gameLoop = new GameLoopThread(rendererManager, world, playerID);
        this.gameLoop.start();
    }

    @Override
    public final GameLoop getGameLoop() {
        return this.gameLoop;
    }

    @Override
    public void gameOver() {
        this.gameLoop.stopLoop();
    }

}
