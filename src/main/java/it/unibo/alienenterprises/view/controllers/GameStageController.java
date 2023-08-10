package it.unibo.alienenterprises.view.controllers;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.InputQueue;
import it.unibo.alienenterprises.controller.bounds.Dimensions;
import it.unibo.alienenterprises.controller.gamesession.GameSession;
import it.unibo.alienenterprises.controller.renderers.Renderable;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.javafx.JFXCanvasPainter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

/**
 * Controller for the GameStage.
 * 
 * @author Giulia Bonifazi
 */
public final class GameStageController implements InitController, Renderable {
    @FXML
    private Label currScore;
    @FXML
    private Label healthDisplay;
    @FXML
    private Canvas canvas;
    @FXML
    private StackPane root;
    @FXML
    private VBox pauseMenu;

    private Dimensions arenaDim;
    private Controller controller;
    private GameSession gameSession;

    private InputQueue keyPressQueue;

    @Override
    public void init(final Controller controller, final Scene scene) {
        this.controller = controller;
        this.arenaDim = this.controller.getArenaDimensions();
        this.root.setPrefWidth(Screen.getPrimary().getBounds().getWidth());
        this.root.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        this.gameSession = controller.getGameSession();
        this.canvas.setWidth(this.arenaDim.getWidth());
        this.canvas.setHeight(this.arenaDim.getHeight());
        scene.setOnKeyPressed(e -> {
            try {
                if (e.getCode() == KeyCode.P) {
                    this.pause();
                }
                this.addKeyPressed(e.getText());
                e.consume();
            } catch (InterruptedException e1) {
            }
        });
        this.keyPressQueue = this.gameSession.startSession(new RendererManager(new JFXCanvasPainter(canvas), this));
    }

    @Override
    public void render() {
        Platform.runLater(() -> {
            if (this.gameSession.isOver()) {
                this.gameOver();
            }
            this.currScore.setText(Integer.toString(this.gameSession.getScore()));
            this.healthDisplay.setText(Integer.toString(this.gameSession.getPlayerHealth()));
        });
    }

    /**
     * Handle the game over.
     */
    @FXML
    public void gameOver() {
        this.gameSession.gameOver();
        this.controller.changeScene(ViewType.GAMEOVER);
    }

    /**
     * Handle the pause button press.
     */
    @FXML
    public void pause() {
        this.pauseMenu.setVisible(true);
        this.gameSession.pause();
    }

    /**
     * Handle resume button click.
     */
    @FXML
    public void resume() {
        this.pauseMenu.setVisible(false);
        this.gameSession.resume();
    }

    /**
     * Adds a String to the {@link InputQueue}.
     * 
     * @param s the text of the key that was pressed
     * @throws InterruptedException
     */
    private void addKeyPressed(final String s) throws InterruptedException {
        this.keyPressQueue.put(s);
    }

}
