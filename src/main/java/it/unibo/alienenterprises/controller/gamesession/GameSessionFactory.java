package it.unibo.alienenterprises.controller.gamesession;

import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;

/**
 * {@link GameSession} factory.
 */
public interface GameSessionFactory {

    /**
     * Builds a single player gamesession.
     * 
     * @return a single player game session.
     */
    public GameSession singleplayerGameSession(CanvasPainter canvasPaint, World world, RendererManager rendererManager);

    /**
     * Builds a multiplayer game session.
     * 
     * @return a multiplayer game session.
     */
    public GameSession multiplayerGameSession();
}
