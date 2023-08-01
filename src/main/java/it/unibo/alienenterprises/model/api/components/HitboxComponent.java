package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Hitbox component interface.
 */
public interface HitboxComponent extends Component {

    /**
     * Different game objects available.
     */
    public enum Type {
        /**
         * Identify the enemis of the game.
         */
        ENEMY,
        /**
         * Identify the player of the game.
         */
        PLAYER,
        /**
         * Identify the projectiles of the game.
         */
        PROJECTILE,
        /**
         * Identify the map's limits.
         */
        BOUNDARY;
    }

    /**
     * Return the object type.
     * 
     * @return object type
     */
    Type getType();

    /**
     * Checks if the object can collide.
     * 
     * @param type
     * @return true if can collide, false otherwise
     */
    Boolean canCollide(Type type);

    /**
     * Check what happens once the two objects have collided.
     * 
     * @return
     */
    void isColliding(GameObject object);
}
