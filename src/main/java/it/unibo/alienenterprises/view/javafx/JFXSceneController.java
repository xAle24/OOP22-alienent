package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.scene.Scene;

public class JFXSceneController implements SceneController {
    private final JFXSceneLoader loader = new JFXSceneLoader();

    private Scene currentScene;
    private InitController currentController;

    public JFXSceneController() {
        this.setCurrentScene(ViewType.GAMESTAGE);
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
            default:
                this.currentScene = new Scene(this.loader.getParent(type));
                this.currentController = this.loader.getCurrentController();
        }
    }

    @Override
    public Scene getCurrentScene() {
        return this.currentScene;
    }

    @Override
    public InitController getCurrentController() {
        return this.currentController;
    }

}
