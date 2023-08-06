package it.unibo.alienenterprises.view;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppStart extends Application {

    public void init(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Controller controller = new ControllerImpl();
        final View view = new ViewImpl(primaryStage);
        view.init(controller);
    }

}
