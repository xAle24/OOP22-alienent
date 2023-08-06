package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.controllers.InitController;
import it.unibo.alienenterprises.view.viewstates.IdleState;
import it.unibo.alienenterprises.view.viewstates.PlayingState;
import it.unibo.alienenterprises.view.viewstates.ViewState;
import javafx.scene.Scene;

public class JFXSceneController implements SceneController {
    private final JFXSceneLoader loader = new JFXSceneLoader();
    private final Controller controller;

    private Scene currentScene;
    private InitController currentController;
    private ViewState viewState;

    public JFXSceneController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setCurrentScene(ViewType type) {
        switch (type) {
            case SHOP:
                break;
            case GAMEOVER:
                break;
            case PAUSE:
                break;
            case GAMESTAGE:
            default:
                this.currentScene = new Scene(this.loader.getParent(type));
                this.currentController = this.loader.getCurrentController();
        }
        this.setState(type);
    }

    @Override
    public Scene getCurrentScene() {
        return this.currentScene;
    }

    @Override
    public InitController getCurrentController() {
        return this.currentController;
    }

    @Override
    public ViewState getViewState() {
        return this.viewState;
    }

    private void setState(ViewType type) {
        if (type == ViewType.GAMESTAGE) {
            this.viewState = new PlayingState(controller,
                    "Are you sure you want to give up?\n(This will trigger a game over)");
        } else {
            this.viewState = new IdleState(controller,
                    "Are you sure you want to quit?\n(Your purchases will be saved)");
        }
    }

}
