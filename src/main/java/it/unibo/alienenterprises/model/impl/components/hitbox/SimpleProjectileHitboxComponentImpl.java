package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;
/**
 * Hitbox implementatios for projectiles.
 */
public class SimpleProjectileHitboxComponentImpl extends CircleHitboxComponentAbs implements ProjectileHitboxComponent {

    private Type shooter;
    /**
     * Constructor for projectiles.
     * @param object referenced object.
     * @param enabled if component is enabled.
     * @param objectType what type of object is.
     * @param radius how long is the circle radius.
     */
    public SimpleProjectileHitboxComponentImpl(final GameObject object, final boolean enabled, 
            final Type objectType, final int radius) {
        super(object, enabled, objectType, radius);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void isColliding(final HitboxComponent hitbox) {
        if (hitbox.getType() != Type.PROJECTILE && hitbox.getType() != shooter) {
            hitbox.getGameObject().hit(super.getGameObject().getStatValue(Statistic.DAMAGE));
        }
    }
    /**
     * Return who shoot the projectiles.
     * @return Type of the shooter.
     */
    @Override
    public Type getShooter() {
        return shooter;
    }
    /**
     * Set who shoot the projectiles.
     * @param type of the shooter.
     */
    @Override
    public void setShooter(final Type type) {
        shooter = type;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.empty();
    }
}
