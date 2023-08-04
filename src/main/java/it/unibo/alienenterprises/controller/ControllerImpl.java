package it.unibo.alienenterprises.controller;

import java.util.Map;

import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactory;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactoryImpl;
import it.unibo.alienenterprises.model.ModelImpl;
import it.unibo.alienenterprises.model.api.Model;
import it.unibo.alienenterprises.view.api.SceneController;

public final class ControllerImpl implements Controller {
    private final Model model;
    private final GameSessionFactory sessionFactory;

    private GameSession currGameSession;

    public ControllerImpl() {
        this.model = new ModelImpl();
        this.sessionFactory = new GameSessionFactoryImpl();
    }

    @Override
    public GameSession getGameSession() {
        return this.currGameSession;
    }

    @Override
    public void initiateGameSession(boolean multiplayer) {
        // this.currGameSession = (multiplayer) ?
        // this.sessionFactory.multiplayerGameSession()
        // : this.sessionFactory.singleplayerGameSession(null, null);
    }

    @Override
    public SceneController getSceneController() {
        return null;
    }

}
