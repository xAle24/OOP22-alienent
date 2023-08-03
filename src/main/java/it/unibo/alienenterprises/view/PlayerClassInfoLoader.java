package it.unibo.alienenterprises.view;

import java.util.Optional;

/**
 * PlayerClassInfoLoader
 * The class that have the job to load the information needed fot the
 * PlayerClassMenu
 */
public interface PlayerClassInfoLoader {

    /**
     * Load in memory the Informations
     */
    void load();

    /**
     * @param name of the class
     * @return
     */
    Optional<PlayerClassInfo> getPlayerClassInfo(String name);

}
