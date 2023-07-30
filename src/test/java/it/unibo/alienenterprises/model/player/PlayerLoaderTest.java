package it.unibo.alienenterprises.model.player;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;

public class PlayerLoaderTest {

    @Test
    public void loadingTest(){
        final ShipLoaderImpl shipLoader = new ShipLoaderImpl();
        var p = shipLoader.loadPlayerClasses();
        System.out.println(p);
        p = shipLoader.loadEnemyClasses();
        System.out.println(p);
    }
    
}
