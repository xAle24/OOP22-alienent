package it.unibo.alienenterprises.view;

/**
 * PlayerClassInfoLoader
 * The class that have the job to load the information needed fot the
 * PlayerClassMenu
 */
public interface PlayerClassInfoLoader {

    /**
     * @param name
     * @return
     */
    PlayerClassInfo getPlayerClassInfo(String name);

}
