package it.unibo.alienenterprises.controller.api;

import it.unibo.alienenterprises.view.SceneType;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.scene.Scene;

/**
 * Interface for the Scene Controller. This controller will be responsible for
 * setting the necessary Scene in the correct way.
 */
public interface SceneController {

    /**
     * Set the current Scene in the Controller.
     * 
     * @param type the type of Scene that is being requested.
     */
    void setCurrentScene(SceneType type);

    /**
     * Get the current scene.
     * 
     * @return the current scene.
     */
    Scene getCurrentScene();

    /**
     * Get the current {@link InitController} that is managing the current Scene.
     * 
     * @return the current controller.
     */
    InitController getCurrentController();
}
