package it.unibo.alienenterprises.controller;

import java.util.Map;

import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.gamesession.GameSessionAbs;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactory;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactoryImpl;
import it.unibo.alienenterprises.model.ModelImpl;
import it.unibo.alienenterprises.model.api.Model;
import it.unibo.alienenterprises.view.SceneType;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.javafx.JFXSceneController;

public final class ControllerImpl implements Controller {
    private final GameSessionFactory sessionFactory;
    private final SceneController sceneController;

    private GameSession currGameSession;

    public ControllerImpl() {
        this.sessionFactory = new GameSessionFactoryImpl();
        this.sceneController = new JFXSceneController();
    }

    @Override
    public GameSession getGameSession() {
        return this.currGameSession;
    }

    @Override
    public void initiateGameSession(boolean multiplayer) {

    }

    @Override
    public SceneController getSceneController() {
        return null;
    }

    @Override
    public void changeScene(SceneType type) {
        this.sceneController.setCurrentScene(type);
    }

}
