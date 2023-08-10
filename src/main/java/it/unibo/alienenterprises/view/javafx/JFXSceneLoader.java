package it.unibo.alienenterprises.view.javafx;

import java.io.IOException;
import java.util.Locale;

import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.api.SceneLoader;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Loads or modifies the current scene according to specifications.
 * 
 * @author Giulia Bonifazi
 */
public final class JFXSceneLoader implements SceneLoader {
    private static final String PATH_START = "/layouts/";
    private static final String PATH_END = ".fxml";
    private FXMLLoader fxmlLoader;

    @Override
    public Parent getParent(final ViewType type) {
        this.fxmlLoader = new FXMLLoader();
        final String path = PATH_START + type.toString().toLowerCase(Locale.ROOT) + PATH_END;
        try {
            final var res = getClass().getResource(path);
            this.fxmlLoader.setLocation(res);
            return this.fxmlLoader.load();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public InitController getCurrentController() {
        return this.fxmlLoader.getController();
    }
}
