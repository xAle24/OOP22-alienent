package it.unibo.alienenterprises.model.world;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.controller.bounds.Dimensions;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent.Locations;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.collisionhandler.CollisionHandler;
import it.unibo.alienenterprises.model.collisionhandler.SimpleCollisionHandler;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.util.DoubleBuffer;
import it.unibo.alienenterprises.model.util.SetDoubleBuffer;
import it.unibo.alienenterprises.model.wall.WallBuilder;
import it.unibo.alienenterprises.model.wall.WallBuilderImpl;

/**
 * Main World of the game. This is where all the objects reside.
 * 
 * @author Giulia Bonifazi
 */
public final class GameWorld implements World {
    /**
     * The ids of every enemy type in the game.
     */
    private static final Set<String> GIVES_SCORE = new HashSet<>(new ShipLoaderImpl().getEnemyIds());
    private final CollisionHandler collisionHandler;
    private final DoubleBuffer<GameObject> doubleBuff;
    private final Set<GameObject> lastAdded;
    private final Dimensions worldDimensions;
    private GameObject player;
    private int score;
    private int enemyCount;

    /**
     * Creates new instance of this class.
     * 
     * @param worldDimensions the dimensions of the game world; these are fixed and
     *                        cannot be changed.
     */
    public GameWorld(final Dimensions worldDimensions) {
        this.worldDimensions = worldDimensions;
        this.enemyCount = 0;
        this.doubleBuff = new SetDoubleBuffer<>();
        this.collisionHandler = new SimpleCollisionHandler();
        this.lastAdded = new HashSet<>();
        this.createWalls();
    }

    @Override
    public void update(final double deltaTime) {
        this.doubleBuff.changeBuffer();
        this.doubleBuff.getCurr().stream().forEach(o -> {
            if (!o.isAlive()) {
                this.removeGameObject(o);
            } else {
                o.update(deltaTime);
            }
        });
        this.collisionHandler.checkCollisions();
    }

    @Override
    public void addGameObject(final GameObject add) {
        if (GIVES_SCORE.contains(add.getId())) {
            this.enemyCount++;
        }
        this.doubleBuff.getBuff().add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
        this.lastAdded.add(add);
    }

    @Override
    public void addAllGameObjects(final GameObject... objects) {
        final var newObj = List.of(objects);
        newObj.forEach(o -> this.addGameObject(o));
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public GameObject getPlayer() {
        return this.player;
    }

    @Override
    public Dimensions getWorldDimensions() {
        return this.worldDimensions;
    }

    @Override
    public Set<GameObject> getLastAdded() {
        final var ret = new HashSet<>(this.lastAdded);
        this.lastAdded.clear();
        return ret;
    }

    @Override
    public void addPlayer(final GameObject player) {
        this.player = player;
        this.addGameObject(player);
    }

    @Override
    public boolean isOver() {
        return !this.player.isAlive();
    }

    @Override
    public int getEnemyCount() {
        return this.enemyCount;
    }

    /**
     * Removes a GameObject from the list of gameobjects at play.
     * 
     * @param remove the GameObject that needs to be removed.
     */
    private void removeGameObject(final GameObject remove) {
        if (GIVES_SCORE.contains(remove.getId())) {
            this.score += remove.getStatValue(Statistic.DAMAGE) * 100;
            this.enemyCount--;
        }
        this.doubleBuff.getBuff().remove(remove);
        this.collisionHandler.removeHitbox(remove.getComponent(HitboxComponent.class));
    }

    private void createWalls() {
        final var walls = new HashSet<GameObject>();
        final WallBuilder wallBuilder = new WallBuilderImpl();
        // Add upper boundary
        wallBuilder.addBoundaryHitboxComponent(new Point2D(0, 0),
                new Point2D(worldDimensions.getWidth(), 0));
        wallBuilder.setLocation(Locations.UP);
        walls.add(wallBuilder.getWall());
        wallBuilder.clear();
        // Add lower boundary
        wallBuilder.addBoundaryHitboxComponent(new Point2D(0, this.worldDimensions.getHeight()),
                new Point2D(this.worldDimensions.getWidth(), this.worldDimensions.getHeight()));
        wallBuilder.setLocation(Locations.DOWN);
        walls.add(wallBuilder.getWall());
        wallBuilder.clear();
        // Add left boundary
        wallBuilder.addBoundaryHitboxComponent(new Point2D(0, 0),
                new Point2D(0, this.worldDimensions.getHeight()));
        wallBuilder.setLocation(Locations.LEFT);
        walls.add(wallBuilder.getWall());
        wallBuilder.clear();
        // Add right boundary
        wallBuilder.addBoundaryHitboxComponent(new Point2D(this.worldDimensions.getWidth(), 0),
                new Point2D(worldDimensions.getWidth(), this.worldDimensions.getHeight()));
        wallBuilder.setLocation(Locations.RIGHT);
        walls.add(wallBuilder.getWall());
        wallBuilder.clear();

        // Add all the walls to the buffer. They do not need a renderer.
        walls.forEach(w -> {
            this.doubleBuff.getBuff().add(w);
            this.collisionHandler.addHitbox(w.getComponent(HitboxComponent.class));
        });
    }
}
