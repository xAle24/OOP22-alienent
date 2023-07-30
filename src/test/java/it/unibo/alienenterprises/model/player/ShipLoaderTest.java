package it.unibo.alienenterprises.model.player;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.impl.components.PlayerInputComponentImpl;

public class ShipLoaderTest {

    @Test
    public void loadingTest() {
        final ShipLoader shipLoader = new ShipLoaderImpl();
        var p = shipLoader.loadPlayerClasses();
        System.out.println(p);

        var standard = p.get("standard");
        standard.getAllComponent().stream().forEach(Component::start);
        System.out.println(standard.getComponent(PlayerInputComponentImpl.class).get().getShooterComponent());
    }

}
