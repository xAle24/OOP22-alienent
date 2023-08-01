package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;

/** Launcher class. */

public final class Alienenterprises {
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
        final Controller controller = new ControllerImpl();
        controller.initiateGameSession(false);
        final View view = new ViewImpl();
        view.start(args);
    }
}
