package it.unibo.alienenterprises.controller;

import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.view.ShipInfoLoader;

public class PlayerControllerImpl implements PlayerController {

    private static final String SEPARATOR = File.separator;
    private static final String PLAYER_CLASS_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "ships" + SEPARATOR + "playerclasses" + SEPARATOR;
    private static final String YAML = ".yml";

    private final ShipInfoLoader info;
    private Optional<String> selected = Optional.empty();

    public PlayerControllerImpl(final World world) {
        this.info = new PlayerInfoLoaderImpl();
        this.info.load();
    }

    @Override
    public Set<String> getPlayerIds() {
        return info.getShipIds();
    }

    @Override
    public Optional<Map<Statistic, Integer>> getStats(final String id) {
        return info.getShipIds().contains(id) ? ShipLoader.loadStatsOf(PLAYER_CLASS_PATH + id + YAML)
                : Optional.empty();
    }

    @Override
    public Optional<String> getName(final String id) {
        return this.info.getShipName(id);
    }

    @Override
    public Optional<String> getDescription(final String id) {
        return this.info.getShipDescription(id);
    }

    @Override
    public Optional<String> getSpritePath(final String id) {
        return this.info.getShipSpriteFilePath(id);
    }

    @Override
    public void select(final String id) {
        if (this.info.getShipIds().contains(id)) {
            this.selected = Optional.of(id);
        }
    }

    @Override
    public void confirmSelection() {
        if (this.selected.isEmpty()) {
            throw new IllegalStateException("nothing has been selected");
        }
    }

}
