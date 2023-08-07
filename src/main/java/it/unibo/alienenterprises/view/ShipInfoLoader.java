package it.unibo.alienenterprises.view;

import java.util.Optional;
import java.util.Set;

/**
 * PlayerClassInfoLoader
 * The class that have the job to load the information needed fot the
 * PlayerClassMenu
 */
public interface ShipInfoLoader {

    /**
     * Load in memory the Informations
     */
    void load();

    /**
     * @return the Set of Ship ids
     */
    Set<String> getShipIds();

    /**
     * @param id
     * @return the name of the ship, if the id is recognized, or an empty Optional
     *         if not
     */
    Optional<String> getShipName(String id);

    /**
     * @param id
     * @return the description of the ship, if the id is recognized, or an empty
     *         Optional if not
     */
    Optional<String> getShipDescription(String id);

    /**
     * @param id
     * @return the SpriteFilePath of the ship, if the id is recognized, or an empty
     *         Optional if not
     */
    Optional<String> getShipSpriteFilePath(String id);

}
