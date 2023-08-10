package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.view.ViewType;
import javafx.stage.Stage;

/**
 * This state will be active while a {@link GameSession} is initiated and the
 * {@link GameLoop} is running.
 * 
 * @author Giulia Bonifazi
 */
public class PlayingState extends ViewStateAbs {

    /**
     * Creates a {@link PlayingState} instance.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param message    the message that will appear in the alert.
     */
    public PlayingState(final Controller controller, final String message) {
        super(controller, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Stage stage) {
        super.init(stage);
        super.getStage().setResizable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCloseRequest() {
        super.getStage().setResizable(true);
        super.getController().getGameSession().gameOver();
        super.getController().changeScene(ViewType.GAMEOVER);
    }

}
