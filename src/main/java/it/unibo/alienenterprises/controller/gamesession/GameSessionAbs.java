package it.unibo.alienenterprises.controller.gamesession;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.renderers.RendererManager;

/**
 * Generic GameSession.
 */
public abstract class GameSessionAbs implements GameSession {
    private int score;
    private GameLoopThread gameLoop;
    protected final World model;

    /**
     * Create an instance of the GameSessionAbs class.
     * 
     * @param view
     * @param world
     */
    public GameSessionAbs(RendererManager rendererManager) {
        this.score = 0;
        this.model = new GameWorld();
        this.setUpSession();
        this.gameLoop = new GameLoopThread(rendererManager, model);
        this.gameLoop.start();
    }

    protected synchronized void addScore(int score) {
        this.score += score;
    }

    protected abstract void setUpSession();

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public final GameLoop getGameLoop() {
        return this.gameLoop;
    }

    @Override
    public void gameOver() {
        this.gameLoop.stopLoop();
        this.model.notifyScore(score);
    }

}
