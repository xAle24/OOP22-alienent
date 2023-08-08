package it.unibo.alienenterprises.controller.api;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import it.unibo.alienenterprises.controller.ShipProp;
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
     * @return the set of the accepted player ids
     */
    Set<String> getPlayerIds();

    /**
     * Loads the enemy classes into memory
     * 
     * @return a map of the enemyClassesNames and objects
     */
    Map<String, GameObject> loadEnemyClasses();

    /**
     * @return the set of the accepted enemy ids
     */
    Set<String> getEnemyIds();

    /**
     * Load the ship identified by the shipFileName in memory
     * 
     * @param shipFileName the complete path to the file
     * @return An Optional containing the GameObject or an empty Optional if there
     *         was any problem whith the loading
     */
    Optional<GameObject> loadShip(String folder, String id);

    /**
     * Load the stat map of the ship identified by the shipFileName
     * 
     * @param shipFileName
     * @return
     */
    static Optional<Map<Statistic, Integer>> loadStatsOf(final String shipFileName) {
        try (final InputStream inputStream = new FileInputStream(shipFileName)) {
            final Yaml yaml = new Yaml();
            final ShipProp obj = yaml.loadAs(inputStream, ShipProp.class);
            final Map<Statistic, Integer> stats = new HashMap<>();
            for (var s : obj.getStats().keySet()) {
                stats.put(Statistic.valueOf(s), obj.getStats().get(s));
            }
            return Optional.of(stats);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}