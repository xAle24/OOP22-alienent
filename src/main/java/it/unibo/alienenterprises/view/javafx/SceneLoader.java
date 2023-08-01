package it.unibo.alienenterprises.view.javafx;

import it.unibo.alienenterprises.view.SceneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Loads or modifies the current scene according to specifications.
 */
public class SceneLoader {
    private static final String PATH_START = "/layouts/";
    private static final String PATH_END = ".fxml";

    public Parent setScene(final SceneType type) {
        final String path = PATH_START + type.toString().toLowerCase() + PATH_END;
        try {
            final Parent root = FXMLLoader.load(this.getClass().getResource(path));
            return root;
        } catch (Exception e) {
            // return a dialog builder window that says "could not load scene"
            e.printStackTrace();
            return null;
        }
    }
}