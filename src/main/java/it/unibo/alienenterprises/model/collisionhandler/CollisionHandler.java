package it.unibo.alienenterprises.model.collisionhandler;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.components.HitboxComponent;

/**
 * Handles collisions during the game session.
 */
public interface CollisionHandler {

    /**
     * Check for collisions among all the game objects currently at play.
     */
    void checkCollisions();

    /**
     * Adds one {@link HitboxComponent} to the collidable list if the
     * {@link Optional} isn't empty.
     * 
     * @param toAdd the gameobject that is to be added.
     */
    void addHitbox(Optional<HitboxComponent> toAdd);

    /**
     * Removes one {@link HitboxComponent} from the collidable list if it is
     * present.
     * 
     * @param toRemove the gameobject that is to be removed.
     */
    void removeHitbox(Optional<HitboxComponent> toRemove);
}
