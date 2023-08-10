package it.unibo.alienenterprises.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.bounds.ArenaDimensions;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.world.GameWorld;
/**
 * Test class for enemySpawner.
 */
public class EnemySpawnerTest {
    private static final Map<Statistic, Integer> statmap = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 1);
    private static final GameObject player = new GameObjectAbs(new Point2D(2, 0), null, statmap, null);
    /**
     * Test to check if the map creation happens correctly.
     */
    @Test
    public void checkMap() {
        var world = new GameWorld(new ArenaDimensions(10.0, 10.0));
        var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10), player);
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
    /**
     * Test to check if Game Object creation happens correctly.
     */
    @Test
    public void checkGameObject() {
        var world = new GameWorld(new ArenaDimensions(10.0, 10.0));
        var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10), player);
        var enemy = enemySpawner.getEnemy("enemySniper", 80);
        assertEquals(enemy, enemy.getComponent(HitboxComponent.class).get().getGameObject());
        assertEquals(enemy, enemy.getComponent(InputComponent.class).get().getGameObject());
    }
}
