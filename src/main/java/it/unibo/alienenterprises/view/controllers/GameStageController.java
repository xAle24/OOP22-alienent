package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.renderers.Renderable;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.javafx.JFXCanvasPainter;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 * Controller for the GameStage.
 * 
 * @author Giulia Bonifazi
 */
public class GameStageController implements InitController, Renderable {
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane root;

    private Controller controller;
    private GameSession gameSession;

    private InputQueue keyPressQueue;

    @Override
    public void init(Controller controller, Scene scene) {
        this.controller = controller;
        this.root.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.root.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.gameSession = controller.getGameSession();
        this.canvas.setWidth(this.gameSession.getWorld().getWorldDimensions().getWidth());
        this.canvas.setHeight(this.gameSession.getWorld().getWorldDimensions().getHeight()
                - this.currScore.getLayoutBounds().getHeight() - this.healthDisplay.getLayoutBounds().getHeight());
        scene.setOnKeyPressed(e -> {
            try {
                this.addKeyPressed(e.getText());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        this.keyPressQueue = this.gameSession.startSession(new RendererManager(new JFXCanvasPainter(canvas), this));
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

    @Override
    public void render() {
        if (this.gameSession.getWorld().isOver()) {
            this.gameSession.gameOver();
            this.controller.changeScene(ViewType.GAMEOVER);
        }
    }
}
