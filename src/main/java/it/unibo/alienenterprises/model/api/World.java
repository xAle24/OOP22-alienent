package it.unibo.alienenterprises.model.api;

import java.util.Set;

/**
 * The main model of the game.
 */
public interface World {

    /**
     * Updates the game world.
     * 
     * @param deltaTime time elapsed since last update
     */
    void update(double deltaTime);

    /**
     * Get the dimensions of the {@link World}
     * 
     * @return
     */
    Dimensions getWorldDimensions();

    /**
     * Adds a GameObject to the list of gameobjects at play.
     * 
     * @param add the GameObject that needs to be added.
     */
    void addGameObject(GameObject add);

    /**
     * Add one or more {@link GameObjects to the World.
     * 
     * @param gameObjects the objects to be added.
     */
    void addAllGameObjects(GameObject... objects);

    /**
     * Get the last added {@link GameObjects} that have not yet generated a
     * {@link Renderer}
     * 
     * @return
     */
    Set<GameObject> getLastAdded();

    /**
     * get the current score
     * 
     * @return the score
     */
    int getScore();

    /**
     * Adds the player to the game world.
     * 
     * @param player
     */
    void addPlayer(GameObject player);

    /**
     * If the player is dead, the game is over. Returns whether the player is alive
     * or not.
     * 
     * @return
     */
    boolean isOver();
}