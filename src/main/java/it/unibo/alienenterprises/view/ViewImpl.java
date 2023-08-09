package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.view.javafx.JFXSceneController;
import it.unibo.alienenterprises.view.javafx.SceneController;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * {@link View} implementation, main view of the game.
 * 
 * @author Giulia Bonifazi
 */
public final class ViewImpl implements View {

    private static final double MIN_WIDTH = 500.0;
    private static final double MIN_HEIGHT = 500.0;
    private static final String TITLE = "Alienent";
    private static final String ICON_PATH = "/images/unlocked.png";

    private final Stage primaryStage;
    private SceneController sceneController;

    /**
     * Creates a new instance of this class.
     * 
     * @param stage the primary stage
     */
    public ViewImpl(final Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setMinHeight(MIN_HEIGHT);
        this.primaryStage.setMinWidth(MIN_WIDTH);
    }

    @Override
    public void init(final Controller controller) {
        this.sceneController = new JFXSceneController(controller);
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));
        this.setScene(ViewType.LOGIN);
    }

    @Override
    public void setScene(ViewType type) {
        this.sceneController.setCurrentScene(type);
        Platform.runLater(() -> {
            this.primaryStage.setScene(this.sceneController.getCurrentScene());
            this.sceneController.getViewState().init(this.primaryStage);
            this.primaryStage.show();
            this.primaryStage.centerOnScreen();
            this.primaryStage.sizeToScene();
        });
    }

    @Override
    public Point2D getWidthHeight() {
        return new Point2D(this.primaryStage.getWidth(), this.primaryStage.getHeight());
    }
}
