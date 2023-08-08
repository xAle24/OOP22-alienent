package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class GameStageController implements InitController {
    @FXML
    private BorderPane canvasContainer;
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    @FXML
    private Canvas canvas;

    private Controller controller;
    private GameLoop gameLoop;
    private GameSession gameSession;

    @Override
    public void init(Controller controller) {
        this.controller = controller;
        this.controller.getGameSession().startSession(new RendererManager(new CanvasPainter(canvas)));
        this.gameLoop = this.gameSession.getGameLoop();
    }

    @FXML
    void handleKeyPressed(KeyEvent event) {
        this.gameLoop.addInput(event.getCharacter());
    }
}
