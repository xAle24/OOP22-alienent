package it.unibo.alienenterprises.view.api;

import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Loader for game {@link Scene} scenes.
 */
public interface SceneLoader {

    /**
     * Returns the {@link Parent} of the new {@link Scene} so it can be shown on
     * screen.
     * 
     * @param type the {@link ViewType} of the {@link Scene}.
     * @return the {@link Parent} of the new {@link Scene} .
     */
    Parent getParent(final ViewType type);

    /**
     * Returns the {@link InitController} of the current {@link Scene}.
     * 
     * @return
     */
    InitController getCurrentController();
}