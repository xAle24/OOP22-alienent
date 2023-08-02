package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;

public class SimpleShipHitboxComponentImpl extends CircleHitboxComponentAbs{

    public SimpleShipHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, double radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(HitboxComponent hitbox) {
        if (hitbox instanceof ProjectileHitboxComponent && ((ProjectileHitboxComponent)hitbox).getShooter() != this.getType()) {
            hitbox.getGameObject().hit(1);
        }
    }

    @Override
    public Optional<Component> duplicate(GameObject obj) {
       return Optional.of(new SimpleShipHitboxComponentImpl(obj, isEnabled(), getType(), getHitbox().getRay()));
    }
    
}
