package it.unibo.alienenterprises.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.alienenterprises.controller.bounds.ArenaDimensions;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.impl.GameObjectImpl;
import it.unibo.alienenterprises.model.world.GameWorld;
/**
 * Test class for enemySpawner.
 */
class EnemySpawnerTest {
    private static final int UPDATETIME61 = 61_000;
    private static final int UPDATETIME60 = 60_000;
    private static final int STAT15 = 15;
    private static final int STAT30 = 30;
    private static final int DELTATIME = 80;
    private static final Map<Statistic, Integer> STATMAP = Map.of(Statistic.HP, 100, Statistic.DAMAGE, 1);
    private static final GameObject PLAYER = new GameObjectImpl(new Point2D(2, 0), null, STATMAP, null);
    /**
     * Test to check if the map creation happens correctly.
     */
    @Test
    void checkMap() {
        final var world = new GameWorld(new ArenaDimensions(10.0, 10.0));
        final var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10), PLAYER);
        enemySpawner.update(UPDATETIME61);
        var map = new HashMap<Statistic, Integer>(enemySpawner.getStats());
        assertEquals(STAT15, map.get(Statistic.HP));
        assertEquals(STAT15, map.get(Statistic.DEFENCE));
        assertEquals(STAT15, map.get(Statistic.DAMAGE));
        enemySpawner.update(UPDATETIME60);
        map = enemySpawner.getStats();
        assertEquals(STAT30, map.get(Statistic.HP));
        assertEquals(STAT30, map.get(Statistic.DEFENCE));
        assertEquals(STAT30, map.get(Statistic.DAMAGE));
    }
    /**
     * Test to check if Game Object creation happens correctly.
     */
    @Test
    void checkGameObject() {
        final var world = new GameWorld(new ArenaDimensions(10.0, 10.0));
        final var enemySpawner = new EnemySpawnerImpl(world, new Point2D(0, 0), new Point2D(10, 10), PLAYER);
        final var enemy = enemySpawner.getEnemy("enemySniper", DELTATIME);
        assertEquals(enemy, enemy.getComponent(HitboxComponent.class).get().getGameObject());
        assertEquals(enemy, enemy.getComponent(InputComponent.class).get().getGameObject());
    }
}
