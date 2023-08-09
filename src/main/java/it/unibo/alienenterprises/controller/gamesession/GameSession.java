package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.world.World;

/**
 * Whenever a "new game" is pressed on the main menu, a new instance of this
 * class will be started.
 * 
 * @author Giulia Bonifazi
 */
public interface GameSession {

    /**
     * Get the current session's {@link GameLoop}.
     * 
     * @return the current session's GameLoop.
     */
    GameLoop getGameLoop();

    /**
     * Handle the game over.
     */
    void gameOver();

    /**
     * Start session after receiving the {@link RendererManager}.
     * 
     * @param rendererManager
     */
    InputQueue startSession(RendererManager rendererManager);

    /**
     * Gets the {@link World}.
     * 
     * @return the world
     */
    World getWorld();
}
