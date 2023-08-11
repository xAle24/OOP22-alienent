package it.unibo.alienenterprises.controller.api;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;

/**
 * Ship Loader.
 * It has the job of getting the stats from the files and to load them in memory
 * as GameObjects
 */
public interface ShipLoader {

    /**
     * Loads the player classes into memory.
     * 
     * @return a map of the playerClassesNames and objects
     */
    Map<String, GameObject> loadPlayerClasses();

    /**
     * @return the set of the accepted player ids
     */
    Set<String> getPlayerIds();

    /**
     * Loads the enemy classes into memory.
     * 
     * @return a map of the enemyClassesNames and objects
     */
    Map<String, GameObject> loadEnemyClasses();

    /**
     * @return the set of the accepted enemy ids
     */
    Set<String> getEnemyIds();

    /**
     * Load the ship with the given id from the given folder.
     * 
     * @param folder the folder in which there is the file
     * @param id     the id of the ship
     * @return An Optional of the ship or an empty Optional if there was any problem
     *         in the loading
     */
    Optional<GameObject> loadShip(String folder, String id);

    /**
     * Load the stat map of the ship identified by the shipFileName.
     * 
     * @param resourceFile
     * @return An Optional of the stat map of the ship relative to the given file or
     *         an empty Optional if there were a problem whith the loading
     */
    Optional<Map<Statistic, Integer>> loadStatsOf(String resourceFile);

}
