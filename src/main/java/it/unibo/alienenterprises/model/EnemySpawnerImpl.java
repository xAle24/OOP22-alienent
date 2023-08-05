package it.unibo.alienenterprises.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.alienenterprises.controller.ShipLoaderImpl;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.ProjectileSupplierFactoryImpl;

public class EnemySpawnerImpl implements EnemySpawner{
    private static final int SPAWNTIME = 800;
    private static final int ONEMINUTE = 60000;
    private Map<String, GameObject> enemy;
    private List<GameObject> newEnemy = new ArrayList<>();
    private double spawnTime;
    private double totalTime;
    private World world;
    private Point2D p1;
    private Point2D p2;

    public EnemySpawnerImpl(final World world, final Point2D x1, final Point2D y2) {
        this.world = world;
        this.p1 = x1;
        this.p2 = y2;
        var projectileFactory = new ProjectileSupplierFactoryImpl(world);
        var loader = new ShipLoaderImpl(projectileFactory);
        enemy = loader.loadEnemyClasses();
        newEnemy = List.copyOf(enemy.values());
    }
    @Override
    public GameObject getEnemy() {
        var randomEnemy = new Random().nextInt(newEnemy.size());
        var enemySpawn = newEnemy.get(randomEnemy);
        enemySpawn.getAllComponent().stream().forEach(e -> e.duplicate(enemySpawn));
        this.getStats().entrySet().stream().forEach(e -> enemySpawn.setStatValue(e.getKey(),enemySpawn.getStatValue(e.getKey()) * e.getValue()));
        var pointX = new Random().nextDouble(p2.getX() - p1.getX() + 1) + p1.getX();
        var pointY = new Random().nextDouble(p2.getY() - p1.getY() + 1) + p1.getY();
        enemySpawn.setPosition(new Point2D(pointX, pointY));
        return enemySpawn;
    }
    @Override
    public HashMap<Statistic, Integer> getStats() {
        var newStats = new HashMap<Statistic, Integer>();
        Integer increase = (int) (15 * (totalTime / ONEMINUTE));
        newStats.put(Statistic.DAMAGE, increase);
        newStats.put(Statistic.HP, increase);
        newStats.put(Statistic.DEFENCE, increase);
        return newStats;
    }
    @Override
    public void update(double deltaTime){
        if (spawnTime > SPAWNTIME) {
            this.world.addGameObject(getEnemy());
            spawnTime = 0;
        } else {
            spawnTime = spawnTime + deltaTime;
        }
        totalTime = totalTime + deltaTime;
    } 
}
