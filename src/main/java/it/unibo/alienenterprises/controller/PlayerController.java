package it.unibo.alienenterprises.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.Statistic;

/**
 * PlayerController
 * Controls the informations that can be shown of the player.
 * It can also have the job of spawning the player.
 */
public interface PlayerController {

    /**
     * @return the ids of the players that the controller owns
     */
    Set<String> getPlayerIds();

    /**
     * @param id the id of the playerClass
     * @return the map of the stats of the playerClass
     */
    Optional<Map<Statistic, Integer>> getStats(String id);

    /**
     * @param id the id of the playerClass
     * @return the Name of the playerClass
     */
    Optional<String> getName(String id);

    /**
     * @param id the id of the playerClass
     * @return the Description of the playerClass
     */
    Optional<String> getDescription(String id);

    /**
     * @param id the id of the playerClass
     * @return the absolute path of the sprite of the playerClass
     */
    Optional<String> getSpritePath(String id);

    /**
     * Set the selected player to the given player
     * 
     * @param id
     */
    void select(String id);

    /**
     * Confirm the selection of the player adding it to the world if necessary
     */
    void confirmSelection();
}