package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.view.javafx.JFXCanvasPainter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Controller for the GameStage.
 */
public class GameStageController implements InitController {
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane root;

    private GameSession gameSession;

    private InputQueue keyPressQueue;

    @Override
    public void init(Controller controller, Scene scene) {
        this.gameSession = controller.getGameSession();
        scene.setOnKeyPressed(e -> {
            try {
                this.addKeyPressed(e.getText());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        this.keyPressQueue = this.gameSession.startSession(new RendererManager(new JFXCanvasPainter(canvas)));
    }

    /**
     * Adds a String to the {@link InputQueue}.
     * 
     * @param s the text of the key that was pressed
     * @throws InterruptedException
     */
    private void addKeyPressed(String s) throws InterruptedException {
        this.keyPressQueue.put(s);
    }
}
