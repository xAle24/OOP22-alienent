package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.api.GameLoop;
import it.unibo.alienenterprises.view.ViewType;
import javafx.stage.Stage;

/**
 * This state will be active while a {@link GameSession} is initiated and the
 * {@link GameLoop} is running.
 * 
 * @author Giulia Bonifazi
 */
public final class PlayingState extends ViewStateAbs {

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
        this.stage.centerOnScreen();
        this.stage.setResizable(false);
    }

    @Override
    protected void onCloseRequest() {
        this.stage.setResizable(true);
        this.controller.getGameSession().gameOver();
        this.controller.changeScene(ViewType.GAMEOVER);
    }

}
