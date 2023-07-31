package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

public class BomberHitboxComponentImpl extends CircleHitboxComponentAbs{

    private static final int BOMBERDAMAGE = 999;

    public BomberHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PLAYER) {
            object.hit(BOMBERDAMAGE);
        }
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PROJECTILE) {
            object.hit(1);
        }
    }
    
}
