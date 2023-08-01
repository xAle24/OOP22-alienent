package it.unibo.alienenterprises.model.player;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.GameWorld;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;

public class ShipLoaderTest {

    private final ShipLoader shipLoader;

    public ShipLoaderTest() {
        this.shipLoader = new ShipLoaderImpl(new ProjectileSupplierFactoryImpl(new GameWorld()));
    }

    @Test
    public void LoadPlayersTest(){
        var playerMap = shipLoader.loadPlayerClasses();
        var standard = playerMap.get("standard");
        System.out.println(standard.getAllStats());

        var tank = playerMap.get("tank");
        System.out.println(tank.getAllStats());

        var sniper = playerMap.get("sniper");
        System.out.println(sniper.getAllStats());
    }

}
