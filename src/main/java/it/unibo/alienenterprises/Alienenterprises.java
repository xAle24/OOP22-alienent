package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/** Launcher class. */

public final class Alienenterprises extends Application {
    // private final Controller controller;
    // private final Model model;

    private Alienenterprises() {
    }

    /*
     * 
     * Main application entry-point.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Controller controller = new ControllerImpl();
        final View view = new ViewImpl(primaryStage);
        view.start(controller);
    }
}
