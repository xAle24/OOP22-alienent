package it.unibo.alienenterprises.controller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.EnemySpawnerImpl;
import it.unibo.alienenterprises.model.PlayerSpawnerImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.InputSupplier;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.InputSupplier.Input;
import it.unibo.alienenterprises.model.api.components.PlayerInputComponent;

/**
 * Implementation of the GameLoop interface.
 */
public final class GameLoopThread extends Thread implements GameLoop {
    private static final long MS_PER_FRAME = 20;
    // private static final int MAX_INPUT = 5;

    private final World world;
    private final RendererManager rendererManager;
    private final EnemySpawner enemySpawner;
    private final InputSupplier inputSupplier;

    private List<Input> inputQueue;
    private boolean stopped;
    private boolean paused;

    /**
     * Create an instance of the GameLoopThread class.
     * 
     * @param rendererManager the {@link RendererManager} responsible for rendering
     *                        the {@link GameObject} instances.
     * @param world           the {@link World} instance of the {@link GameSession}
     * @param playerID        the ID of the chosen player class.
     */
    public GameLoopThread(RendererManager rendererManager, final World world, final String playerID) {
        this.world = world;
        this.rendererManager = rendererManager;
        this.inputQueue = new ArrayList<>();
        var player = new PlayerSpawnerImpl(world).getPlayer(playerID).get();
        this.inputSupplier = player.getComponent(PlayerInputComponent.class).get().getInputSupplier();
        this.enemySpawner = new EnemySpawnerImpl(world, null, null, player);
        this.rendererManager.addRenderer(player, playerID);
        this.stopped = false;
        this.paused = false;
    }

    @Override
    public void run() {
        long previousStart = System.currentTimeMillis();
        while (!this.stopped) {
            if (this.paused) {
                synchronized (this) {
                    while (this.paused) {
                        try {
                            this.wait();
                        } catch (Exception e) {

                        }
                    }
                }
            }
            long currentStart = System.currentTimeMillis();
            long elapsed = currentStart - previousStart;
            this.processInput();
            this.updateGame(elapsed);
            this.render();
            this.waitForNextFrame(System.currentTimeMillis() - currentStart);
            previousStart = currentStart;
        }
    }

    private void waitForNextFrame(final long delta) {
        if (delta < MS_PER_FRAME) {
            try {
                Thread.sleep(MS_PER_FRAME - delta);
            } catch (Exception e) {
            }
        }
    }

    private void processInput() {
        for (Input input : this.inputQueue) {
            this.inputSupplier.addInput(input);
        }
    }

    @Override
    public void updateGame(final double deltaTime) {
        this.enemySpawner.update(deltaTime);
        this.world.update(deltaTime);
        this.world.getLastAdded().forEach(o -> this.rendererManager.addRenderer(o, o.getId()));
    }

    @Override
    public synchronized void pauseLoop() {
        this.paused = true;
    }

    @Override
    public synchronized void resumeLoop() {
        this.paused = false;
        this.notifyAll();
    }

    @Override
    public synchronized void stopLoop() {
        this.stopped = true;
        this.interrupt();
    }

    @Override
    public synchronized void addInput(String string) {
        Input input;
        switch (string) {
            case "w", "W":
                input = Input.ACCELERATE;
                break;
            case "s", "S":
                input = Input.STOP_ACCELERATE;
                break;
            case "a", "A":
                input = Input.TURN_LEFT;
                break;
            case "d", "D":
                input = Input.TURN_RIGHT;
            case " ":
                input = Input.SHOOT;
                break;
            default:
                return;
        }
        this.inputQueue.add(input);
    }

    private void render() {
        this.rendererManager.render();
    }

}
