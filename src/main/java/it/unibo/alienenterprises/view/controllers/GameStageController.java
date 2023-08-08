package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.view.javafx.CanvasPainter;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class GameStageController implements InitController {
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    @FXML
    private Canvas canvas;

    private GameSession gameSession;

    @Override
    public void init(Controller controller) {
        this.gameSession = controller.getGameSession();
        this.gameSession.startSession(new RendererManager(new CanvasPainter(canvas)));
    }

    @FXML
    void handleKeyPressed(KeyEvent event) {
        this.gameSession.getGameLoop().addInput(event.getCharacter());
    }
}
