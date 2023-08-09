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
import it.unibo.alienenterprises.model.world.World;
import it.unibo.alienenterprises.model.api.components.EnemyInputComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;
/**
 * Implementations of enemy spawner.
 */
public class EnemySpawnerImpl implements EnemySpawner {
    private static final int SPAWNTIME = 2;
    private static final int ONEMINUTE = 60000;
    private static final int INCREASESTAT = 15;
    private static final Random RANDOM = new Random();
    private Map<String, GameObject> enemy;
    private List<String> enemyList;
    private double spawnTime;
    private double totalTime;
    private World world;
    private Point2D bottomLeft;
    private Point2D topRight;
    private final GameObject player;
    /**
     * Constructor for the enemy spawner.
     * @param world
     * @param x1 bottom-left value 
     * @param y2 top-right value
     */
    public EnemySpawnerImpl(final World world, final Point2D x1, final Point2D y2, final GameObject player) {
        this.world = world;
        this.bottomLeft = x1;
        this.topRight = y2;
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
    public GameObject getEnemy(String identifier, double deltaTime) {
        var enemySpawn = enemy.get(identifier);
        var newEnemy = new GameObjectAbs(null, null, enemySpawn.getAllStats(), enemySpawn.getId());
        enemySpawn.getAllComponent().stream().forEach(e -> newEnemy.addComponent(e.duplicate(newEnemy).get()));
        newEnemy.getComponent(EnemyInputComponent.class).get().setTarget(player);
        this.getStats().entrySet().stream().forEach(e -> newEnemy.setStatValue(e.getKey(), 
            newEnemy.getStatValue(e.getKey()) + newEnemy.getStatValue(e.getKey()) * e.getValue()));
        System.out.println(newEnemy.getStatValue(Statistic.DAMAGE)+ ""+ enemySpawn.getStatValue(Statistic.DAMAGE));
        var pointX = new Random().nextDouble(topRight.getX() + 1);
        var pointY = new Random().nextDouble(bottomLeft.getY() + 1);
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
            this.world.addGameObject(getEnemy(id, deltaTime));
            spawnTime = 0;
            System.out.println(id);

        } else {
            spawnTime = spawnTime + deltaTime;
        }
        totalTime = totalTime + deltaTime;
    } 
}
