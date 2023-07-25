package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Ship;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;

public class PlayerClassLoaderImpl {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "playerclasses";

    public Optional<Ship> loadStandardPlayer() {
        try (InputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "standard.yml")){
            final Yaml yaml = new Yaml();
            final var obj = yaml.loadAs(inputStream,ShipProp.class);
            System.out.println(obj);
            Ship playerClass = null;
            return Optional.ofNullable(playerClass);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Ship translate(final ShipProp prop){
        final Map<Statistic,Integer> stats = new HashMap<>();
        for(final var s : Statistic.values()){
            stats.put(s, prop.getStats().get(s.toString()));
        }
        final InputComponent input = null;// TODO InputComponentFactory.get(prop.getInput());
        final HitboxComponent hitbox = null;// TODO HitboxFactory.get(prop.getHitboxID(),prop.getHitboxr());
        final ShooterComponent shooter = null;//TODO ShooterComponentFactory.get(prop.getShooter());
        return null;
    }

}
