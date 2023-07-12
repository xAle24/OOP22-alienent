package it.unibo.alienenterprises.model.collisionhandler;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Handles collisions during the game session.
 */
public interface CollisionHandler {

    /**
     * Check for collisions among all the game objects currently at play.
     */
    void checkCollisions();

    /**
     * Adds one {@link Gameobject} to the collidable list.
     * 
     * @param toAdd the gameobject that is to be added.
     */
    void addGameObject(GameObject toAdd);

    /**
     * Removes one {@link Gameobject} from the collidable list.
     * 
     * @param toRemove the gameobject that is to be removed.
     */
    void removeGameObject(GameObject toRemove);
}
