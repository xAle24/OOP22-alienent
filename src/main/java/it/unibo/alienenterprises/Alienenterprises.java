package it.unibo.alienenterprises;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.ControllerImpl;
import it.unibo.alienenterprises.controller.GameLoopThread;
import it.unibo.alienenterprises.controller.gamesession.GameSessionAbs;
import it.unibo.alienenterprises.controller.gamesession.GameSessionFactoryImpl;
import it.unibo.alienenterprises.controller.renderers.RendererManager;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.GiuliaShip;
import it.unibo.alienenterprises.model.api.GameSession;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.GiuliaHitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Circle2D;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.GiuliaHitboxComponentCircle;
import it.unibo.alienenterprises.model.impl.components.GiuliaHitboxComponentLine;
import it.unibo.alienenterprises.view.View;
import it.unibo.alienenterprises.view.ViewImpl;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
