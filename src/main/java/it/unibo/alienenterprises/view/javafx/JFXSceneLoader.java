package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.api.SceneLoader;
import it.unibo.alienenterprises.view.controllers.InitController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Loads or modifies the current scene according to specifications.
 */
public class JFXSceneLoader implements SceneLoader {
    private static final String PATH_START = "/layouts/";
    private static final String PATH_END = ".fxml";
    private final FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public Parent getParent(final ViewType type) {
        final String path = PATH_START + type.toString().toLowerCase() + PATH_END;
        try {
            final Parent root = this.fxmlLoader.load(getClass().getResourceAsStream(path));
            return root;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InitController getCurrentController() {
        return fxmlLoader.getController();
    }
}
