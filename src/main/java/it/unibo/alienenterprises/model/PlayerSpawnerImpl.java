package it.unibo.alienenterprises.model;

import java.util.Optional;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.api.ShipLoader;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.PlayerSpawner;
import it.unibo.alienenterprises.model.world.World;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;

/**
 * PlayerSpawnerImpl.
 */
public class PlayerSpawnerImpl implements PlayerSpawner {

    private static final String PLAYER_FOLDER = "/ships/playerclasses/";
    private static final double RIGHT_ANGLE = 90;

    private final World world;
    private final ShipLoader shipLoader;

    /**
     * @param world the GameWorld
     */
    public PlayerSpawnerImpl(final World world) {
        this.world = world;
        this.shipLoader = new ShipLoaderImpl(new ProjectileSupplierFactoryImpl(world));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameObject> getPlayer(final String id) {
        if (this.shipLoader.getPlayerIds().contains(id)) {
            final var opPlayer = this.shipLoader.loadShip(PLAYER_FOLDER, id);
            if (opPlayer.isPresent()) {
                final var player = opPlayer.get();
                player.setPosition(new Point2D(this.world.getWorldDimensions().getWidth() / 2,
                        this.world.getWorldDimensions().getHeight() / 2));
                player.setVelocity(Vector2D.fromAngleAndModule(RIGHT_ANGLE, 0));
            }
            return opPlayer;
        }
        return Optional.empty();
    }
}
