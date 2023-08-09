package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;
/**
 * Bomber hitbox component implementations.
 */
public class BomberHitboxComponentImpl extends CircleHitboxComponentAbs {

    private static final int AUTOKILLDAMAGE = 99;
    /**
     * Constructor for Bomber enemis.
     * @param object referenced object.
     * @param enabled if component is enabled.
     * @param objectType what type of object is.
     * @param radius how long is the circle radius.
     */
    public BomberHitboxComponentImpl(final GameObject object, final boolean enabled, final Type objectType, final double radius) {
        super(object, enabled, objectType, radius);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void isColliding(final HitboxComponent hitbox) {
        if (hitbox.getType() == Type.PLAYER) {
            hitbox.getGameObject().hit(this.getGameObject().getStatValue(Statistic.DAMAGE));
            this.getGameObject().hit(AUTOKILLDAMAGE);
        }
        if (hitbox instanceof ProjectileHitboxComponent && ((ProjectileHitboxComponent)hitbox).getShooter() == Type.PLAYER) {
            hitbox.getGameObject().hit(1);
        }
    }
    /**
     * @inheritDoc
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new BomberHitboxComponentImpl(obj, isEnabled(), Type.ENEMY, this.getHitbox().getRay()));
    }
}
