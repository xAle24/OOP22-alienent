package it.unibo.alienenterprises.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import it.unibo.alienenterprises.controller.gamesession.GameSessionFactoryImpl;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.GiuliaShip;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.GiuliaHitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.GiuliaHitboxComponentCircle;
import it.unibo.alienenterprises.model.impl.components.GiuliaHitboxComponentLine;
import it.unibo.alienenterprises.model.impl.components.InputComponentSniperImpl;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class GameStageController implements Initializable {
    @FXML
    private StackPane canvasContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // var gameSessionFac = new GameSessionFactoryImpl();
        // World world = new GameWorld();
        // var giulia1 = new GiuliaShip();
        // var giulia2 = new GiuliaShip();
        // giulia2.setVelocity(new Vector2D(0, 0));
        // giulia2.setPosition(new Point2D(250, 250));
        // giulia1.addComponent(new InputComponentSniperImpl(giulia1, true, giulia2));
        // giulia1.addComponent(new GiuliaHitboxComponentCircle(giulia1, true,
        // Type.ENEMY));
        // giulia2.addComponent(new GiuliaHitboxComponentLine(giulia2, true,
        // Type.WALL));
        // world.addGameObject(giulia1);
        // world.addGameObject(giulia2);
        // giulia1.setVelocity(new Vector2D(2, 0));
        // giulia1.setPosition(new Point2D(100, 100));
        // CanvasPainter canvasPaint = new CanvasPainter(500, 500);
        // var renderMan = new RendererManager(canvasPaint);
        // renderMan.addRenderer(giulia1);
        // renderMan.addRenderer(giulia2);
        // var gameSession = gameSessionFac.singleplayerGameSession(canvasPaint, world,
        // renderMan);
        // this.canvasContainer.getChildren().add(canvasPaint.getCanvas());
    }

}
