package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.EnemySpawnerImpl;
import it.unibo.alienenterprises.model.PlayerSpawnerImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.InputSupplier;
import it.unibo.alienenterprises.model.api.UserAccount;
import it.unibo.alienenterprises.model.api.components.PlayerInputComponent;
import it.unibo.alienenterprises.model.api.components.PowerUpComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.world.World;

/**
 * Implementation of the {@link GameLoop} interface.
 * 
 * @author Giulia Bonifazi
 */
public final class GameLoopThread extends Thread implements GameLoop {
    private static final long MS_PER_FRAME = 20;

    private final World world;
    private final RendererManager rendererManager;
    private final EnemySpawner enemySpawner;
    private final InputSupplier inputSupplier;
    private final InputQueue inputQueue;

    private boolean stopped;
    private boolean paused;

    /**
     * Create an instance of the GameLoopThread class.
     * 
     * @param queue           the {@link InputQueue} that catches the inputs from
     *                        the GameStage.
     * @param rendererManager the {@link RendererManager} responsible for rendering
     *                        the {@link GameObject} instances.
     * @param world           the {@link World} instance of the {@link GameSession}
     * @param playerID        the ID of the chosen player class.
     * @param account         the {@link UserAccount} of the current user.
     */
    public GameLoopThread(final InputQueue queue, final RendererManager rendererManager, final World world,
            final String playerID, final UserAccount account) {
        this.inputQueue = queue;
        this.world = world;
        this.rendererManager = rendererManager;

        // Set up the player
        var player = new PlayerSpawnerImpl(world).getPlayer(playerID).get();
        this.inputSupplier = player.getComponent(PlayerInputComponent.class).get().getInputSupplier();
        player.getComponent(PowerUpComponent.class).get().setPoweUps(account.getToAddPwu());
        this.world.addPlayer(player);

        // Set up the EnemySpawner
        var topRight = new Point2D(this.world.getWorldDimensions().getWidth(), 0);
        var bottomLeft = new Point2D(0, this.world.getWorldDimensions().getHeight());
        this.enemySpawner = new EnemySpawnerImpl(world, bottomLeft, topRight, player);
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
            this.updateGame(elapsed / 1000.0);
            this.render();
            this.waitForNextFrame(System.currentTimeMillis() - currentStart);
            previousStart = currentStart;
        }
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

    /**
     * Waits for the next frame if the pre-established time between frames hasn't
     * already passed.
     * 
     * @param delta the time spent completing the frame.
     */
    private void waitForNextFrame(final long delta) {
        if (delta < MS_PER_FRAME) {
            try {
                Thread.sleep(MS_PER_FRAME - delta);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Updates the {@link GameWorld} and adds its newest {@link GameObject}
     * instances to {@link RendererManager}.
     * 
     * @param deltaTime
     */
    private void updateGame(final double deltaTime) {
        this.enemySpawner.update(deltaTime);
        this.world.update(deltaTime);
        this.world.getLastAdded().forEach(o -> this.rendererManager.addRenderer(o, o.getId()));
    }

    /**
     * Adds the inputs to the {@link InputSupplier} of the player one at a time.
     */
    private void processInput() {
        while (this.inputQueue.size() != 0) {
            this.inputSupplier.addInput(this.inputQueue.pollInput());
        }
    }

    /**
     * Renders the {@link GameObject} that are currently alive.
     */
    private void render() {
        this.rendererManager.render();
    }

}
