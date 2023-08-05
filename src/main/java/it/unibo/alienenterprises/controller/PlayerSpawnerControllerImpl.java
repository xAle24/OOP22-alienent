package it.unibo.alienenterprises.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
import it.unibo.alienenterprises.view.PlayerClassInfo;
import it.unibo.alienenterprises.view.PlayerClassInfoLoader;
import it.unibo.alienenterprises.view.PlayerClassInfoLoaderImpl;

public class PlayerSpawnerControllerImpl {

    public final Map<String, GameObject> shipMap;
    public final PlayerClassInfoLoader infoLoader;

    public Optional<String> selected = Optional.empty();

    public PlayerSpawnerControllerImpl(final World world) {
        this.shipMap = new ShipLoaderImpl(new ProjectileSupplierFactoryImpl(world)).loadPlayerClasses();
        this.infoLoader = new PlayerClassInfoLoaderImpl(this.shipMap.keySet());
        this.infoLoader.load();
    }

    public Set<String> getIdSet() {
        return shipMap.keySet();
    }

    public Optional<Map<Statistic, Integer>> getStatsOf(final String id) {
        var op = this.shipMap.get(id);
        return op != null ? Optional.of(op.getAllStats()) : Optional.empty();
    }

    public Optional<PlayerClassInfo> getInfoOf(final String id) {
        return infoLoader.getPlayerClassInfo(id);
    }

    public Optional<String> getSelectedId() {
        return selected;
    }

    public Optional<GameObject> getSelectedObject() {
        return selected.isPresent() ? Optional.of(shipMap.get(selected.get())) : Optional.empty();
    }

    public void setSelected(final String id) {
        if (shipMap.containsKey(id)) {
            this.selected = Optional.of(id);
        }
    }
}
