package it.unibo.alienenterprises.model.api;

import java.util.Optional;

/* Handles collisions during the game session. */
public interface CollisionHandler {
    
    /* Check for collisions among all the game objects currently at play */
    public Optional<GameObject> checkCollisions();
}
