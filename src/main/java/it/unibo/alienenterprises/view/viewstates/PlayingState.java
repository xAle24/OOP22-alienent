package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.stage.Stage;

/**
 * This state will be active while a {@link GameSession} is initiated and the
 * {@link GameLoop} is running.
 */
public final class PlayingState extends ViewStateAbs {
    public static final double WIDTH = 1920;
    public static final double HEIGHT = 1080;

    /**
     * Creates a {@link PlayingState} instance.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param message    the message that will appear in the alert.
     */
    public PlayingState(final Controller controller, final String message) {
        super(controller, message);
    }

    @Override
    public void init(Stage stage) {
        super.init(stage);
        this.stage.setWidth(PlayingState.WIDTH);
        this.stage.setHeight(PlayingState.HEIGHT);
        this.stage.setResizable(false);
    }

    @Override
    protected void onCloseRequest() {
        this.controller.getGameSession().gameOver();
        this.controller.changeScene(ViewType.GAMEOVER);
    }

}
