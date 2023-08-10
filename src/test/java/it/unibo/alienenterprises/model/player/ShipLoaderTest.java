package it.unibo.alienenterprises.model.player;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.controller.bounds.ArenaDimensions;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
import it.unibo.alienenterprises.model.world.GameWorld;

public class ShipLoaderTest {

    private final ShipLoader shipLoader;
    private final Set<String> playerIds;
    private final Set<String> enemyIds;

    public ShipLoaderTest() {
        this.shipLoader = new ShipLoaderImpl(
                new ProjectileSupplierFactoryImpl(new GameWorld(new ArenaDimensions(20, 20))));
        this.playerIds = this.shipLoader.getPlayerIds();
        this.enemyIds = this.shipLoader.getEnemyIds();
    }

    @Test
    public void LoadPlayersTest() {
        var map = this.shipLoader.loadPlayerClasses();
        for (var ship : this.playerIds) {
            assertNotNull(map.get(ship), "the following ship didn't load: " + ship);
            assertTrue(!map.get(ship).getAllComponent().isEmpty(), "the components of" + ship + " didn't load");
        }
    }

    @Test
    public void LoadEnemiesTest() {
        var map = this.shipLoader.loadEnemyClasses();
        for (var ship : this.enemyIds) {
            assertNotNull(map.get(ship), "the following ship didn't load: " + ship);
            assertTrue(!map.get(ship).getAllComponent().isEmpty(), "the components of" + ship + " didn't load");
        }
    }

}
