package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.EnemySpawnerImpl;
import it.unibo.alienenterprises.model.PlayerSpawnerImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.World;

/**
 * Implementation of the GameLoop interface.
 */
public final class GameLoopThread extends Thread implements GameLoop {
    private static final long MS_PER_FRAME = 20;
    private static final int MAX_INPUT = 5;
    private final World world;
    private final RendererManager rendererManager;
    private final EnemySpawner enemySpawner;
    // private Map<UserTag, String> inputQueue;
    private boolean stopped;
    private boolean paused;

    /**
     * Create an instance of the GameLoopThread class.
     * 
     * @param view
     * @param world
     */
    public GameLoopThread(RendererManager rendererManager, final World world, final String playerID) {
        this.world = world;
        this.rendererManager = rendererManager;
        var player = new PlayerSpawnerImpl(world).getPlayer(playerID).get();
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
        // this.inputQueue.forEach(i -> {

        // });
    }

    @Override
    public void updateGame(final double deltaTime) {
        this.enemySpawner.update(deltaTime);
        this.world.update(deltaTime);
        // this.world.getLastAdded().forEach(o -> this.rendererManager.addRenderer(o,
        // o.getID()));
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

    private void render() {
        this.rendererManager.render();
    }

    // private synchronized void addInput(Input input) {
    // this.inputQueue.add(input);
    // }
}
