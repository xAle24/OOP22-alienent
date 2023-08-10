package it.unibo.alienenterprises.view.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.alienenterprises.model.api.Statistic;
import javafx.scene.image.Image;

/**
 * PlayerController.
 * Controls the informations that can be shown of the player.
 * It can also have the job of spawning the player.
 */
public interface PlayerController extends InitController {

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
     * @return the Optional of the requested image or an empty Optional if the id
     *         doesn't correspond to any sprite
     */
    Optional<Image> getSpriteImage(String id);

    /**
     * Set the selected player to the given player.
     * 
     * @param id
     */
    void select(String id);

    /**
     * Confirm the selection of the player and starts the game.
     */
    void confirmSelection();

    /**
     * exit the selection.
     */
    void exit();
}
