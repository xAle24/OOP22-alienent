package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.EnemyInputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
/**
 * Implementations of enemy spawner.
 */
public class EnemySpawnerImpl implements EnemySpawner {
    private static final int SPAWNTIME = 800;
    private static final int ONEMINUTE = 60000;
    private static final int INCREASESTAT = 15;
    private static final Random RANDOM = new Random();
    private Map<String, GameObject> enemy;
    private List<String> enemyList;
    private double spawnTime;
    private double totalTime;
    private World world;
    private Point2D p1;
    private Point2D p2;
    private final GameObject player;
    /**
     * Constructor for the enemy spawner.
     * @param world
     * @param x1
     * @param y2
     */
    public EnemySpawnerImpl(final World world, final Point2D x1, final Point2D y2, final GameObject player) {
        this.world = world;
        this.p1 = x1;
        this.p2 = y2;
        this.player = player;
        var projectileFactory = new ProjectileSupplierFactoryImpl(world);
        var loader = new ShipLoaderImpl(projectileFactory);
        enemy = loader.loadEnemyClasses();
        enemyList = List.copyOf(enemy.keySet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getEnemy(String identifier) {
        var enemySpawn = enemy.get(identifier);
        var newEnemy = new GameObjectAbs(null, null, enemySpawn.getAllStats());
        enemySpawn.getAllComponent().stream().forEach(e -> newEnemy.addComponent(e.duplicate(newEnemy).get()));
        newEnemy.getComponent(EnemyInputComponent.class).get().setTarget(player);
        this.getStats().entrySet().stream().forEach(e -> newEnemy.setStatValue(e.getKey(), 
            newEnemy.getStatValue(e.getKey()) * e.getValue()));
        var pointX = new Random().nextDouble(p2.getX() - p1.getX() + 1) + p1.getX();
        var pointY = new Random().nextDouble(p2.getY() - p1.getY() + 1) + p1.getY();
        newEnemy.setPosition(new Point2D(pointX, pointY));
        return newEnemy;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public HashMap<Statistic, Integer> getStats() {
        var newStats = new HashMap<Statistic, Integer>();
        Integer increase = (int) (INCREASESTAT * (totalTime / ONEMINUTE));
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
        if (spawnTime > SPAWNTIME) {
            var id = getIdentifier();
            this.world.addGameObject(getEnemy(id));
            spawnTime = 0;
        } else {
            spawnTime = spawnTime + deltaTime;
        }
        totalTime = totalTime + deltaTime;
    } 
}
