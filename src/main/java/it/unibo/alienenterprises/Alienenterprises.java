package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.view.AppStart;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher class.
 */
public final class Alienenterprises {
    /*
     * 
     * Main application entry-point.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        var App = new AppStart();
        App.init(args);
    }
}
