package it.unibo.alienenterprises.model.player;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.api.ShipLoader;

public class PlayerLoaderTest {

    @Test
    public void loadingTest() {
        final ShipLoader shipLoader = new ShipLoaderImpl();
        var p = shipLoader.loadPlayerClasses();
        System.out.println(p);
        p = shipLoader.loadEnemyClasses();
        System.out.println(p);
    }

}
