package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.view.SceneType;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.scene.Scene;

public class JFXSceneController implements SceneController {
    private final SceneLoader loader = new SceneLoader();

    private Scene currentScene;

    public JFXSceneController() {
        this.setCurrentScene(SceneType.MAINMENU);
    }

    @Override
    public void setCurrentScene(SceneType type) {
        switch (type) {
            case SHOP:
                break;
            case GAMEOVER:
                break;
            case PAUSE:
                break;
            default:
                this.loader.setScene(type);
        }
    }

    @Override
    public Scene getCurrentScene() {
        return this.currentScene;
    }

    @Override
    public InitController getCurrentController() {
        return this.loader.getCurrentController();
    }

}
