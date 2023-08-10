package it.unibo.alienenterprises.view.controllers;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.controller.Controller;
import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.view.ShipInfoLoader;
import it.unibo.alienenterprises.view.ViewType;
import it.unibo.alienenterprises.view.javafx.PlayerInfoLoaderImpl;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * PlayerControllerImpl.
 * Implemetntation of PlayerController
 */
public class PlayerControllerImpl implements PlayerController {

    private static final String SEPARATOR = File.separator;
    private static final String PLAYER_CLASS_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "ships" + SEPARATOR + "playerclasses" + SEPARATOR;
    private static final String YAML = ".yml";

    private final ShipInfoLoader info;
    private Optional<String> selected = Optional.empty();
    private Optional<Controller> controller = Optional.empty();

    /**
     * Set up a PlayerController.
     */
    public PlayerControllerImpl() {
        this.info = new PlayerInfoLoaderImpl();
        this.info.load();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Controller controller, final Scene scene) {
        this.controller = Optional.of(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getPlayerIds() {
        return info.getShipIds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<Statistic, Integer>> getStats(final String id) {
        return info.getShipIds().contains(id) ? ShipLoader.loadStatsOf(PLAYER_CLASS_PATH + id + YAML)
                : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getName(final String id) {
        return this.info.getShipName(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> getDescription(final String id) {
        return this.info.getShipDescription(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Image> getSpriteImage(final String id) {
        return this.info.getShipImage(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void select(final String id) {
        if (this.info.getShipIds().contains(id)) {
            this.selected = Optional.of(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmSelection() {
        if (this.selected.isEmpty()) {
            throw new IllegalStateException("nothing has been selected");
        }
        if (this.controller.isEmpty()) {
            throw new IllegalStateException("the controller has not been initialized");
        }
        // TODO settare il player
        this.controller.get().initiateGameSession(selected.get());
        this.controller.get().changeScene(ViewType.GAMESTAGE);
    }

    @Override
    public void exit(){
        if (this.controller.isEmpty()) {
            throw new IllegalStateException("the controller has not been initialized");
        }
        this.controller.get().changeScene(ViewType.MAINMENU);
    }

    

}
