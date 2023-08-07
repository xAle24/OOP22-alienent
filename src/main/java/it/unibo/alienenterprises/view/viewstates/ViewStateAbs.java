package it.unibo.alienenterprises.view.viewstates;

import it.unibo.alienenterprises.controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * General {@link ViewState} implementation.
 */
public abstract class ViewStateAbs implements ViewState {
    private final String message;
    protected final Controller controller;
    protected Stage stage;

    /**
     * Creates an instance of the {@link ViewStateAbs} class
     * 
     * @param controller the main {@link Controller} of the game.
     * @param message    the message that will appear in the alert.
     */
    public ViewStateAbs(final Controller controller, final String message) {
        this.controller = controller;
        this.message = message;
    }

    @Override
    public void init(final Stage stage) {
        this.stage = stage;
        this.stage.setOnCloseRequest(e -> {
            generateAlert(message);
            e.consume();
        });
    }

    private void generateAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.initOwner(stage);
        alert.showAndWait().filter(ans -> ans == ButtonType.OK).ifPresent(ans -> onCloseRequest());
    }

    protected abstract void onCloseRequest();
}
