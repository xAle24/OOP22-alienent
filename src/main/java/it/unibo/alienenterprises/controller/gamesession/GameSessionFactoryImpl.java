package it.unibo.alienenterprises.controller.gamesession;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;

public class GameSessionFactoryImpl implements GameSessionFactory {

    @Override
    public GameSession singleplayerGameSession(CanvasPainter canvasPaint, World world,
            RendererManager rendererManager) {
        return new GameSessionAbs(canvasPaint, world, rendererManager) {

        };
    }

    @Override
    public GameSession multiplayerGameSession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiplayerGameSession'");
    }

}
