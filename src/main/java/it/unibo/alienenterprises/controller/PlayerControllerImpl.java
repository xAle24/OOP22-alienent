package it.unibo.alienenterprises.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
import it.unibo.alienenterprises.view.ShipInfoLoader;

public class PlayerControllerImpl implements PlayerController {

    private final World world;
    private final Map<String, GameObject> playerMap;
    private final ShipInfoLoader info;
    private Optional<String> selected = Optional.empty();

    public PlayerControllerImpl(final World world) {
        this.world = world;
        this.playerMap = new ShipLoaderImpl(new ProjectileSupplierFactoryImpl(world)).loadPlayerClasses();
        this.info = new PlayerInfoLoaderImpl(playerMap.keySet());
        this.info.load();
    }

    @Override
    public Set<String> getPlayerIds() {
        return playerMap.keySet();
    }

    @Override
    public Optional<Map<Statistic, Integer>> getStats(final String id) {
        return playerMap.containsKey(id) ? Optional.of(playerMap.get(id).getAllStats()) : Optional.empty();
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
        if (this.playerMap.containsKey(id)) {
            this.selected = Optional.of(id);
        }
    }

    @Override
    public void confirmSelection() {
        if (this.selected.isEmpty()) {
            throw new IllegalStateException("nothing has been selected");
        }
        final var player = this.playerMap.get(this.selected.get());
        this.world.addGameObject(player);
        player.setVelocity(Vector2D.fromAngleAndModule(90, player.getVelocity().getModule()));
    }

}
