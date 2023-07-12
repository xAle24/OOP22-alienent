package it.unibo.alienenterprises.controller.gamesession;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Model;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;

/**
 * Generic GameSession.
 */
public abstract class GameSessionAbs implements GameSession {
    private int score;
    private Set<GameObject> gameObjects;
    private GameLoop gameLoop;
    private final View view;
    private final Model model;

    /**
     * Create an instance of the GameSessionAbs class.
     * 
     * @param view
     * @param world
     */
    public GameSessionAbs(final View view, final Model model) {
        this.score = 0;
        this.view = view;
        this.model = model;
        this.gameObjects = new HashSet<>();
        this.gameLoop = new GameLoopThread(view);
    }

    protected synchronized void addScore(int score) {
        this.score += score;
    }

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
