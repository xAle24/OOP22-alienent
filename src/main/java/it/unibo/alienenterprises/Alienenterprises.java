package it.unibo.alienenterprises;

import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;

/** Launcher class. */

public final class Alienenterprises {
    private Alienenterprises() { }

    /**
     * Main application entry-point.
     * @param args
     */
    public static void main(final String[] args) {
        final View view = new ViewImpl();
        view.start(args);
    }
}
