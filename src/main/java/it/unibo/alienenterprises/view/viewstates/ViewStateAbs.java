package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * General {@link ViewState} implementation.
 * 
 * @author Giulia Bonifazi
 */
public abstract class ViewStateAbs implements ViewState {
    private final String message;
    private final Controller controller;
    private Stage stage;

    /**
     * Creates an instance of the {@link ViewStateAbs} class.
     * 
     * @param controller the main {@link Controller} of the game.
     * @param message    the message that will appear in the alert.
     */
    public ViewStateAbs(final Controller controller, final String message) {
        this.controller = controller;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Stage stage) {
        this.stage = stage;
        this.stage.setResizable(true);
        this.stage.setOnCloseRequest(e -> {
            generateAlert(message);
            e.consume();
        });
    }

    /**
     * {@inheritDoc}
     * Generates an alert.
     * 
     * @param message the message on the alert.
     */
    private void generateAlert(final String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.initOwner(stage);
        alert.showAndWait().filter(ans -> ans == ButtonType.OK).ifPresent(ans -> onCloseRequest());
    }

    /**
     * {@inheritDoc}
     * Gets the stage.
     * 
     * @return the stage
     */
    protected Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     * Gets the controller.
     * 
     * @return the controller.
     */
    protected Controller getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     * Reaction to close request.
     */
    protected void onCloseRequest() {
        this.stage.close();
    }
}
