package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
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
    private Controller controller;
    private final Stage primaryStage;

    public ViewImpl(final Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void init(final Controller controller) {
        this.controller = controller;
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.setMinHeight(MIN_HEIGHT);
        this.primaryStage.setMinWidth(MIN_WIDTH);
        this.controller.SetView(this);
        this.controller.changeScene(ViewType.MAINMENU);
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
        this.primaryStage.setScene(new Scene(new BorderPane()));
        this.primaryStage.show();
    }

    @Override
    public void setScene() {
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
        this.primaryStage.show();
    }

}
