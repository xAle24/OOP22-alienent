package it.unibo.alienenterprises.model.api;

import java.util.Optional;

/**
 * PlayerSpawner.
 */
public interface PlayerSpawner {

    /**
     * @param id the id of the player
     * @return an Optional of the player or an empty Optional if there were problems
     *         in the loading or if the given id do not represent a playerClass
     */
    Optional<GameObject> getPlayer(String id);

}
