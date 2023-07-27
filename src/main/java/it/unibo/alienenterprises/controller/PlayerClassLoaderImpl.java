package it.unibo.alienenterprises.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.yaml.snakeyaml.Yaml;
import it.unibo.alienenterprises.model.api.ProjectileSupplierFactory;
import it.unibo.alienenterprises.model.api.Ship;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;
import it.unibo.alienenterprises.model.impl.InputSupplierImpl;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
import it.unibo.alienenterprises.model.impl.components.BasicShooterComponent;
import it.unibo.alienenterprises.model.impl.components.PlayerInputComponentImpl;

public class PlayerClassLoaderImpl {

    private static final String SEPARATOR = File.separator;
    private static final String GAME_PATH = "src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR
            + "playerclasses";

    private final ProjectileSupplierFactory pFactory = new ProjectileSupplierFactoryImpl();

    public Optional<Ship> loadStandardPlayer() {
        try (InputStream inputStream = new FileInputStream(GAME_PATH + SEPARATOR + "standard.yml")) {
            final Yaml yaml = new Yaml();
            final var obj = yaml.loadAs(inputStream, ShipProp.class);
            System.out.println(obj);
            Ship playerClass = translate(obj);
            return Optional.ofNullable(playerClass);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Ship translate(final ShipProp prop) {
        final Map<Statistic, Integer> stats = new HashMap<>();
        for (final var s : Statistic.values()) {
            stats.put(s, prop.getStats().get(s.toString()));
        }
        final InputComponent input = new PlayerInputComponentImpl(null, new InputSupplierImpl());
        // TODO InputComponentFactory.get(prop.getInput());
        final HitboxComponent hitbox = null;
        // TODO HitboxFactory.get(prop.getHitboxID(),prop.getHitboxr());
        final ShooterComponent shooter = new BasicShooterComponent(null, true,
                pFactory.getBasicProjectileSupplier(),
                stats.get(Statistic.COOLDOWN));
        // TODO ShooterComponentFactory.get(prop.getShooter());
        return null;
    }

}
