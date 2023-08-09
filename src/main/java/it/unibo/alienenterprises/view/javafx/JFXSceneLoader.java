package it.unibo.alienenterprises.view.javafx;

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
        final String path = PATH_START + type.toString().toLowerCase() + PATH_END;
        try {
            var res = getClass().getResource(path);
            this.fxmlLoader.setLocation(res);
            final Parent root = this.fxmlLoader.load();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InitController getCurrentController() {
        return this.fxmlLoader.getController();
    }
}
