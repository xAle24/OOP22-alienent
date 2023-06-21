package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.geometry.Geometry2D.Circle2D;

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
        PROJECTILE;
    }
    /**
     * Return the hitbox circle.
     * @return hitbox circle
     */
    Circle2D getCircle2D();
    /**
     * Return the object type. 
     * @return object type
     */
    Type getType();
    /**
     * Checks if the object can collide.
     * @param type
     * @return true if can collide, false otherwise
     */
    Boolean canCollide(Type type);
}
