package it.unibo.alienenterprises.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;

public class EnemySpawnerTest {
    
    @Test
    public void checkMap() {
        var world = new GameWorld();
        var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10));
        enemySpawner.update(61000);
        var map = new HashMap<Statistic, Integer>();
        map = enemySpawner.getStats();
        assertEquals(15, map.get(Statistic.HP));
        assertEquals(15, map.get(Statistic.DEFENCE));
        assertEquals(15, map.get(Statistic.DAMAGE));
        enemySpawner.update(60000);
        map = enemySpawner.getStats();
        assertEquals(30, map.get(Statistic.HP));
        assertEquals(30, map.get(Statistic.DEFENCE));
        assertEquals(30, map.get(Statistic.DAMAGE));
    }

    @Test
    public void checkGameObject() {
        var world = new GameWorld();
        var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10));
        var enemy = enemySpawner.getEnemy();
        assertEquals(enemy, enemy.getComponent(HitboxComponent.class).get().getGameObject());
        assertEquals(enemy, enemy.getComponent(InputComponent.class).get().getGameObject());
    }
}
