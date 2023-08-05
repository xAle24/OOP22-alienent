package it.unibo.alienenterprises.model.api;

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
     * Adds a GameObject to the list of gameobjects at play.
     * 
     * @param add the GameObject that needs to be added.
     */
    public void addGameObject(GameObject add);

    /**
     * Removes a GameObject from the list of gameobjects at play.
     * 
     * @param remove the GameObject that needs to be removed.
     */
    public void removeGameObject(GameObject remove);

    /**
     * Add one or more {@link GameObjects to the World.
     * 
     * @param gameObjects the objects to be added.
     */
    public void addAllGameObjects(GameObject... objects);

    /**
     * When the game is over, pass the score to the model
     * 
     * @param score the end game score
     */
    public void notifyScore(int score);
}