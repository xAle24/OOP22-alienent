package it.unibo.alienenterprises.controller.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * Ship Loader
 * It has the job of getting the stats from the files and to load them in memory
 * as GameObjects
 */
public interface ShipLoader {

    /**
     * Loads the player classes into memory
     * 
     * @return a map of the playerClassesNames and objects
     */
    Map<String, GameObject> loadPlayerClasses();

    /**
     * Loads the enemy classes into memory
     * 
     * @return a map of the enemyClassesNames and objects
     */
    Map<String, GameObject> loadEnemyClasses();

    /**
     * Load the ship identified by the shipFileName in memory
     * 
     * @param shipFileName the complete path to the file
     * @return An Optional containing the GameObject or an empty Optional if there
     *         was any problem whith the loading
     */
    Optional<GameObject> loadShip(String shipFileName);

    /**
     * Load the stat map of the ship identified by the shipFileName
     * 
     * @param shipFileName
     * @return
     */
    Optional<Map<Statistic, Integer>> loadStatsOf(String shipFileName);

}