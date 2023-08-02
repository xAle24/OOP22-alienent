package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.JFXSceneController;
import it.unibo.alienenterprises.controller.api.SceneController;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl implements View {

    private final SceneController sceneController;
    private Controller controller;
    private Stage primaryStage;

    public ViewImpl(Stage stage) {
        this.primaryStage = stage;
        this.sceneController = new JFXSceneController();
        this.primaryStage.setTitle("Alienent");
        this.primaryStage.setMinHeight(500);
        this.primaryStage.setMinWidth(500);
        this.primaryStage.setScene(this.sceneController.getCurrentScene());
    }

    @Override
    public void start(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void setScene() {
        this.primaryStage.setScene(this.sceneController.getCurrentScene());
    }

}
