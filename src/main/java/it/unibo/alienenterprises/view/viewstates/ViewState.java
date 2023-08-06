package it.unibo.alienenterprises.view.viewstates;

import javafx.stage.Stage;

/**
 * This interface is implemented by the various states the view can activate.
 * Only one can be active at once.
 */
public interface ViewState {

    /**
     * Initializes the {@link ViewState}.
     * 
     * @param stage the primaryStage of the view.
     */
    void init(Stage stage);

}
