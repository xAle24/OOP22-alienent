package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;
/**
 * Implementations of the basic ship hitbox.
 */
public class SimpleShipHitboxComponentImpl extends CircleHitboxComponentAbs {
    /**
     * Constructor for all basic ship hitbox.
     * @param object referenced object.
     * @param enabled if component is enabled.
     * @param objectType what type of object is.
     * @param radius how long is the circle radius.
     */
    public SimpleShipHitboxComponentImpl(final GameObject object, final boolean enabled, 
            final Type objectType, final double radius) {
        super(object, enabled, objectType, radius);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void isColliding(final HitboxComponent hitbox) {
        if (hitbox instanceof ProjectileHitboxComponent && ((ProjectileHitboxComponent) hitbox).getShooter() != this.getType()) {
            hitbox.getGameObject().hit(1);
        }
    }
    /**
     * @inheritDoc
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
       return Optional.of(new SimpleShipHitboxComponentImpl(obj, isEnabled(), getType(), getHitbox().getRay()));
    }
}
