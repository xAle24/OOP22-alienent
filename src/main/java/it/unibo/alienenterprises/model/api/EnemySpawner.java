package it.unibo.alienenterprises.model.api;

import java.util.HashMap;

/**
 * Spawn enemis in the game.
 */
public interface EnemySpawner {
    /**
     * Creates a new enemy ready to be spawned.
     * @return a new enemy
     */
    GameObject getEnemy();
    /**
     * Create a map of percentage stats with which to update those of enemies.
     * @return a map with increase value to apply at enemy's map 
     */
    HashMap<Statistic, Integer> getStats();
    /**
     * Create a new enemy if the specified time has passed.
     * @param deltaTime
     */
    void update(double deltaTime);
}
