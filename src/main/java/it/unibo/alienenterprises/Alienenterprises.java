package it.unibo.alienenterprises;

/**
 * Launcher class.
 * 
 * @author Giulia Bonifazi
 */
public final class Alienenterprises {

    /**
     * Constructor for Alienenterprises.
     */
    private Alienenterprises() {

    }

    /**
     * Main program entry point
     * 
     * @param args args
     */
    public static void main(final String[] args) {
        var app = new AppStart();
        app.init(args);
    }
}
