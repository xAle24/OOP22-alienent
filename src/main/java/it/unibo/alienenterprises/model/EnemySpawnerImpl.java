package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.world.World;
import it.unibo.alienenterprises.model.api.components.EnemyInputComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.GameObjectImpl;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
/**
 * Implementations of enemy spawner.
 */
public class EnemySpawnerImpl implements EnemySpawner {
    private static final int SPAWNTIME = 4;
    private static final int ONEMINUTE = 60_000;
    private static final int INCREASESTAT = 15;
    private static final int MAXENEMYINMAP = 50;
    private static final Random RANDOM = new Random();
    private final Map<String, GameObject> enemy;
    private final List<String> enemyList;
    private double spawnTime;
    private double totalTime;
    private final World world;
    private final Point2D bottomLeft;
    private final Point2D topRight;
    private final GameObject player;
    /**
     * Constructor for the enemy spawner.
     * @param world
     * @param x1 bottom-left value 
     * @param y2 top-right value
     * @param player target for enemis
     */
    public EnemySpawnerImpl(final World world, final Point2D x1, final Point2D y2, final GameObject player) {
        this.world = world;
        this.bottomLeft = x1;
        this.topRight = y2;
        this.player = player;
        final var projectileFactory = new ProjectileSupplierFactoryImpl(world);
        final var loader = new ShipLoaderImpl(projectileFactory);
        enemy = loader.loadEnemyClasses();
        enemyList = List.copyOf(enemy.keySet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEnemy(final String identifier, final double deltaTime) {
        final var enemySpawn = enemy.get(identifier);
        final var newEnemy = new GameObjectImpl(null, null, enemySpawn.getAllStats(), enemySpawn.getId());
        enemySpawn.getAllComponent().stream().forEach(e -> newEnemy.addComponent(e.duplicate(newEnemy).get()));
        newEnemy.getComponent(EnemyInputComponent.class).get().setTarget(player);
        this.getStats().entrySet().stream().forEach(e -> newEnemy.setStatValue(e.getKey(), 
            newEnemy.getStatValue(e.getKey()) + newEnemy.getStatValue(e.getKey()) * e.getValue()));
        final var pointX = new Random().nextDouble(topRight.getX() + 1);
        final var pointY = new Random().nextDouble(bottomLeft.getY() + 1);
        newEnemy.setPosition(new Point2D(pointX, pointY));
        newEnemy.getComponent(InputComponent.class).get().update(deltaTime);
        newEnemy.getAllComponent().stream().forEach(e -> e.start());
        return newEnemy;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<Statistic, Integer> getStats() {
        final var newStats = new HashMap<Statistic, Integer>();
        final Integer increase = (int) (INCREASESTAT * (totalTime / ONEMINUTE));
        newStats.put(Statistic.DAMAGE, increase);
        newStats.put(Statistic.HP, increase);
        newStats.put(Statistic.DEFENCE, increase);
        return newStats;
    }
    private String getIdentifier() {
        return enemyList.get(RANDOM.nextInt(enemyList.size()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltaTime) {
        if (spawnTime > SPAWNTIME && this.world.getEnemyCount() < MAXENEMYINMAP) {
            final var id = getIdentifier();
            this.world.addGameObject(getEnemy(id, deltaTime));
            spawnTime = 0;
        } else {
            spawnTime = spawnTime + deltaTime;
        }
        totalTime = totalTime + deltaTime;
    } 
}
