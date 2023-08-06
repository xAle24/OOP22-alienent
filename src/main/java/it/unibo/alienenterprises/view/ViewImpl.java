package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.InputComponentSniperImpl;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.javafx.SceneLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl extends Application implements View {

    public ViewImpl(final Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        var gameSessionFac = new GameSessionFactoryImpl();
        var statMap1 = new HashMap<Statistic, Integer>();
        statMap1.put(Statistic.HP, 2);
        var statMap2 = new HashMap<Statistic, Integer>();
        statMap2.put(Statistic.HP, 1);
        World world = new GameWorld();
        var giulia1 = new GameObjectAbs(new Point2D(50, 50),
                new Vector2D(0, 0),
                statMap1);
        var giulia2 = new GameObjectAbs(new Point2D(250, 250), new Vector2D(5, 5),
                statMap2);
        giulia1.addComponent(new InputComponentSniperImpl(giulia1, true, giulia2));
        giulia1.addComponent(new BomberHitboxComponentImpl(giulia1, true, Type.ENEMY, 50));
        giulia2.addComponent(new SimpleShipHitboxComponentImpl(giulia2, true, Type.PLAYER, 50));
        giulia2.addComponent(new StraightInputComponent(giulia2, true));
        world.addGameObject(giulia1);
        world.addGameObject(giulia2);
        giulia1.setVelocity(new Vector2D(2, 0));
        giulia1.setPosition(new Point2D(100, 100));
        CanvasPainter canvasPaint = new CanvasPainter(500, 500);
        var renderMan = new RendererManager(canvasPaint);
        renderMan.addRenderer(giulia1);
        renderMan.addRenderer(giulia2);
        var gameSession = gameSessionFac.singleplayerGameSession(canvasPaint, world,
                renderMan);

        StackPane pane = new StackPane(canvasPaint.getCanvas());
        this.primaryStage = primaryStage;
        Scene scene = new Scene(pane);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * Program's entry point.
     * 
     * @param args
     */

    @Override
    public void start(final String[] args) {
        launch(args);
    }

}
