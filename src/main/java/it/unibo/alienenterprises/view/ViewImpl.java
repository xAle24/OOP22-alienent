package it.unibo.alienenterprises.view;

import java.util.HashMap;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactoryImpl;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.InputSupplier;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.InputSupplier.Input;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.InputSupplierImpl;
import it.unibo.alienenterprises.model.impl.components.InputComponentSniperImpl;
import it.unibo.alienenterprises.model.impl.components.PlayerInputComponentImpl;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.BomberHitboxComponentImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleShipHitboxComponentImpl;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import it.unibo.alienenterprises.view.javafx.SceneLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl implements View {

    private final SceneLoader loader = new SceneLoader();
    private Stage primaryStage;

    public void start(final Stage primaryStage) throws Exception {
        var gameSessionFac = new GameSessionFactoryImpl();
        var statMap1 = new HashMap<Statistic, Integer>();
        statMap1.put(Statistic.HP, 2);
        statMap1.put(Statistic.DAMAGE, 1);
        var statMap2 = new HashMap<Statistic, Integer>();
        statMap2.put(Statistic.HP, 1);
        statMap2.put(Statistic.DAMAGE, 2);
        statMap2.put(Statistic.SPEED, 5);
        World world = new GameWorld();
        var enemy = new GameObjectAbs(new Point2D(100, 100),
                new Vector2D(5, 5),
                statMap1);
        var player = new GameObjectAbs(new Point2D(250, 250),
                new Vector2D(0, 0),
                statMap2);
        InputSupplier playerInputs = new InputSupplierImpl();
        var inputComponent = new PlayerInputComponentImpl(player, playerInputs);
        inputComponent.start();
        player.addComponent(inputComponent);
        enemy.addComponent(new BomberHitboxComponentImpl(enemy, true, Type.ENEMY, 50));
        player.addComponent(new SimpleShipHitboxComponentImpl(player, true, Type.PLAYER, 50));
        enemy.addComponent(new StraightInputComponent(enemy, true));
        world.addGameObject(enemy);
        world.addGameObject(player);
        CanvasPainter canvasPaint = new CanvasPainter(500, 500);
        var renderMan = new RendererManager(canvasPaint);
        renderMan.addRenderer(enemy);
        renderMan.addRenderer(player);
        var gameSession = gameSessionFac.singleplayerGameSession(canvasPaint, world,
                renderMan);

        StackPane pane = new StackPane(canvasPaint.getCanvas());
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(e -> {
            switch (e.getText()) {
                case "w":
                    System.out.println(e.getText());
                    playerInputs.addInput(Input.ACCELERATE);
                    break;
                case "a":
                    System.out.println(e.getText());
                    playerInputs.addInput(Input.TURN_LEFT);
                    break;
                case "d":
                    System.out.println(e.getText());
                    playerInputs.addInput(Input.TURN_RIGHT);
                    break;
                case "s":
                    System.out.println(e.getText());
                    playerInputs.addInput(Input.STOP_ACCELERATE);
                    break;
            }
        });
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }
}
