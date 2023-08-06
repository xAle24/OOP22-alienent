package it.unibo.alienenterprises.controller;

import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.model.UserAccountHandlerImpl;
import it.unibo.alienenterprises.model.UserAccountImpl;
import it.unibo.alienenterprises.model.api.UserAccountHandler;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.javafx.JFXSceneController;

public final class ControllerImpl implements Controller {
    private final SceneController sceneController;
    private final UserAccountHandler accHandler;
    private final View view;
    private UserAccountImpl account;

    private GameSession currGameSession;

    public ControllerImpl(final View view) {
        this.sceneController = new JFXSceneController(this);
        this.accHandler = new UserAccountHandlerImpl();
        this.view = view;
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
        return this.sceneController;
    }

    @Override
    public void changeScene(ViewType type) {
        this.sceneController.setCurrentScene(type);
        this.sceneController.getCurrentController().init(this);
        this.view.setScene(this.sceneController.getViewState());
    }

    @Override
    public UserAccountHandler getUserAccountHandler() {
        return this.accHandler;
    }

    @Override
    public void save() {
        this.accHandler.save(this.account);
    }
}
