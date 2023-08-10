package it.unibo.alienenterprises.model.api;

import java.util.Map;

/**
 * Spawn enemis in the game.
 */
public interface EnemySpawner {
    /**
     * Creates a new enemy ready to be spawned.
     * @param identifier of the enemy you want to get.
     * @param deltaTime Time passed since the last cycle.
     * @return a new enemy
     */
    GameObject getEnemy(String identifier, double deltaTime);
    /**
     * Create a map of percentage stats with which to update those of enemies.
     * @return a map with increase value to apply at enemy's map 
     */
    Map<Statistic, Integer> getStats();
    /**
     * Create a new enemy if the specified time has passed.
     * @param deltaTime
     */
    void update(double deltaTime);
}
