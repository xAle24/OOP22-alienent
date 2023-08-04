package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.javafx.JFXSceneController;
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
        this.primaryStage.show();
    }

    @Override
    public void setScene() {
        this.primaryStage.setScene(this.sceneController.getCurrentScene());
        this.primaryStage.show();
    }

}
