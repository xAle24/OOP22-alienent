package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.view.api.SceneController;
import it.unibo.alienenterprises.view.javafx.JFXSceneController;
import it.unibo.alienenterprises.view.viewstates.ViewState;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl implements View {

    private static final double MIN_WIDTH = 500.0;
    private static final double MIN_HEIGHT = 500.0;
    private static final String TITLE = "Alienent";

    private final Stage primaryStage;
    private SceneController sceneController;

    private Controller controller;

    public ViewImpl(final Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void init(final Controller controller) {
        this.controller = controller;
        this.sceneController = new JFXSceneController(controller);
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.setMinHeight(MIN_HEIGHT);
        this.primaryStage.setMinWidth(MIN_WIDTH);
        this.setScene(ViewType.REGISTRATION);
    }

    @Override
    public void setScene(ViewType type) {
        this.sceneController.setCurrentScene(type);
        this.sceneController.getViewState().init(this.primaryStage);
        this.primaryStage.setScene(this.sceneController.getCurrentScene());
        this.primaryStage.show();
    }

    @Override
    public Point2D getWidthHeight() {
        return new Point2D(this.primaryStage.getWidth(), this.primaryStage.getHeight());
    }
}
