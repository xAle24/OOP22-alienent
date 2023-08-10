package it.unibo.alienenterprises.model.api.components;
/**
 * Interface for all enemis input component.
 */
public interface ProjectileHitboxComponent extends HitboxComponent {
    /**
     * Get who shoot the projectile.
     * @return type of the shooter.
     */
    Type getShooter();
    /**
     * Set who shoot the projectiles.
     * @param type type of game object that shoot projectiles.
     */
    void setShooter(Type type);
}
