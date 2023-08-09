package it.unibo.alienenterprises.model.api;

import java.util.Set;

/**
 * This class contains model logic for when the {@link GameSession} is running.
 * 
 * @author Giulia Bonifazi
 */
public interface World {

    /**
     * Updates the game world.
     * 
     * @param deltaTime time elapsed since last update.
     */
    void update(double deltaTime);

    /**
     * Get the dimensions of the {@link World}.
     * 
     * @return the dimensions of the world.
     */
    Dimensions getWorldDimensions();

    /**
     * Adds a GameObject to the list of gameobjects at play.
     * 
     * @param add the GameObject that needs to be added.
     */
    void addGameObject(GameObject add);

    /**
     * Add one or more {@link GameObjects} to the World.
     * 
     * @param objects the objects/s to be added.
     */
    void addAllGameObjects(GameObject... objects);

    /**
     * Get the last added {@link GameObjects} that have not yet generated a
     * {@link Renderer}.
     * 
     * @return the gameobjects that have not yet received a renderer.
     */
    Set<GameObject> getLastAdded();

    /**
     * Get the current score.
     * 
     * @return the score.
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
     * @return if the match is over.
     */
    boolean isOver();

}