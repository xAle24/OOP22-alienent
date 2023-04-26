package it.unibo.alienenterprises.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Alien enterprises game jafaFX main.
 */
public final class ViewImpl extends Application implements View {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Label message = new Label("Welcome to alien enterprises"); 
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }

    /**
     * Program's entry point.
     * @param args
     */

    @Override
    public void start(final String[] args) {
        launch(args);
    }

}
