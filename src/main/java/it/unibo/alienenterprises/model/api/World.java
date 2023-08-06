package it.unibo.alienenterprises.model.api;

import it.unibo.alienenterprises.model.WorldDimensions;

/**
 * The main model of the game.
 */
public interface World {

    /**
     * Updates the game world.
     * 
     * @param deltaTime time elapsed since last update
     */
    public void update(double deltaTime);

    /**
     * Get the dimensions of the {@link World}
     * 
     * @return
     */
    public Dimensions getWorldDimensions();

    /**
     * Adds a GameObject to the list of gameobjects at play.
     * 
     * @param add the GameObject that needs to be added.
     */
    public void addGameObject(GameObject add);

    /**
     * Add one or more {@link GameObjects to the World.
     * 
     * @param gameObjects the objects to be added.
     */
    public void addAllGameObjects(GameObject... objects);

    /**
     * get the current score
     * 
     * @return the score
     */
    public int getScore();
}