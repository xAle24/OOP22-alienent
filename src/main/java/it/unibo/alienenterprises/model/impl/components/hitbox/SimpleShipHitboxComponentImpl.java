package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

public class SimpleShipHitboxComponentImpl extends CircleHitboxComponentAbs{

    public SimpleShipHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PROJECTILE) {
            object.hit(1);
        }
    }
    
}
