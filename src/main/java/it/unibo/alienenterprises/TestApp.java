package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestApp extends Application {

    ViewImpl view;
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {

        controller = new ControllerImpl();
        controller.initiateGameSession(false);
        view = new ViewImpl();
        view.start(primaryStage);
    }

    public void StartApp(final String[] args) {
        launch(args);
    }

}
