package it.unibo.alienenterprises.model.api.components;

/**
 * Hitbox component interface.
 */
public interface HitboxComponent extends Component {

    /**
     * Different game objects available.
     */
    enum Type {
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
     * @return object type
     */
    Type getType();

    /**
     * Checks if the object can collide.
     * @param hitbox hitbox of the object it is colliding with.
     */
    void canCollide(HitboxComponent hitbox);

    /**
     * Check what happens once the two objects have collided.
     * @param hitbox hitbox of the object it is colliding with.
     */
    void isColliding(HitboxComponent hitbox);
}
