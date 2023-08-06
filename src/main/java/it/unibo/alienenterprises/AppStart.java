package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class starts the JavaFX thread and launches the game within it.
 */
public class AppStart extends Application {

    /**
     * Initialize the {@link Application}
     * 
     * @param args
     */
    public void init(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final View view = new ViewImpl(primaryStage);
        final Controller controller = new ControllerImpl(view);
        view.init(controller);
    }

}
