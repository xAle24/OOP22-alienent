package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;

public class SimpleShipHitboxComponentImpl extends CircleHitboxComponentAbs{

    public SimpleShipHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(HitboxComponent hitbox) {
        if (hitbox instanceof ProjectileHitboxComponent && ((ProjectileHitboxComponent)hitbox).getShooter() != this.getType()) {
            hitbox.getGameObject().hit(1);
        }
    }
    
}
