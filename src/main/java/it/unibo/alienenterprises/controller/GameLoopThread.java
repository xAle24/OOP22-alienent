package it.unibo.alienenterprises.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.renderers.RendererManager;

/**
 * Implementation of the GameLoop interface.
 */
public final class GameLoopThread extends Thread implements GameLoop {
    private static final long MS_PER_FRAME = 1000;
    private static final int MAX_INPUT = 5;
    private final World world;
    private final RendererManager rendererManager;
    private Map<UserTag, String> inputQueue;
    private boolean stopped;
    private boolean paused;

    /**
     * Create an instance of the GameLoopThread class.
     * 
     * @param view
     * @param world
     */
    public GameLoopThread(RendererManager rendererManager, final World world) {
        this.world = world;
        this.rendererManager = rendererManager;
        this.stopped = false;
        this.paused = false;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
            System.out.println("Ciao" + currentStart);
            long elapsed = currentStart - previousStart;
            this.processInput();
            this.updateGame(elapsed / 1000.0);
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
        this.world.update(deltaTime);
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
