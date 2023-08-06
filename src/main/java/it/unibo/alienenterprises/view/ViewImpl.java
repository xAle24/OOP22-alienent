package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.InputComponentSniperImpl;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.viewstates.ViewState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl extends Application implements View {

    private static final double MIN_WIDTH = 500.0;
    private static final double MIN_HEIGHT = 500.0;
    private static final String TITLE = "Alienent";

    private final Stage primaryStage;

    private Controller controller;

    public ViewImpl(final Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void init(final Controller controller) {
        this.controller = controller;
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.setMinHeight(MIN_HEIGHT);
        this.primaryStage.setMinWidth(MIN_WIDTH);
        this.controller.changeScene(ViewType.GAMESTAGE);
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
        this.primaryStage.setScene(new Scene(new BorderPane()));
        this.primaryStage.show();
    }

    @Override
    public void setScene(ViewState state) {
        state.init(this.primaryStage);
        this.primaryStage.setScene(this.controller.getSceneController().getCurrentScene());
        this.primaryStage.show();
    }

    @Override
    public Point2D getWidthHeight() {
        return new Point2D(this.primaryStage.getWidth(), this.primaryStage.getHeight());
    }
}
