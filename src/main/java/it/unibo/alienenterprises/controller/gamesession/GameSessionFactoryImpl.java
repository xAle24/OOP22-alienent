package it.unibo.alienenterprises.controller.gamesession;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.renderers.RendererManager;

public class GameSessionFactoryImpl implements GameSessionFactory {

    @Override
    public GameSession singleplayerGameSession(World world,
            RendererManager rendererManager) {
        return new GameSessionAbs(rendererManager) {

            @Override
            protected void setUpSession() {

                this.model.addAllGameObjects();
            }
        };
    }

    @Override
    public GameSession multiplayerGameSession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multiplayerGameSession'");
    }

}
