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

/**
 * Test Class for ShipLoader..
 */
final class ShipLoaderTest {

    private static final GameWorld WORLD = new GameWorld(new ArenaDimensions(20, 20));
    private final ShipLoader shipLoader;
    private final Set<String> playerIds;
    private final Set<String> enemyIds;

    ShipLoaderTest() {
        this.shipLoader = new ShipLoaderImpl(
                new ProjectileSupplierFactoryImpl(WORLD));
        this.playerIds = this.shipLoader.getPlayerIds();
        this.enemyIds = this.shipLoader.getEnemyIds();
    }

    /**
     * Test if the players are loaded correctly.
     */
    @Test
    void loadPlayersTest() {
        final var map = this.shipLoader.loadPlayerClasses();
        for (final var ship : this.playerIds) {
            assertNotNull(map.get(ship), "the following ship didn't load: " + ship);
            assertTrue(!map.get(ship).getAllComponent().isEmpty(), "the components of" + ship + " didn't load");
        }
    }

    /**
     * Test if the players are loaded correctly.
     */
    @Test
    void loadEnemiesTest() {
        final var map = this.shipLoader.loadEnemyClasses();
        for (final var ship : this.enemyIds) {
            assertNotNull(map.get(ship), "the following ship didn't load: " + ship);
            assertTrue(!map.get(ship).getAllComponent().isEmpty(), "the components of" + ship + " didn't load");
        }
    }

}
