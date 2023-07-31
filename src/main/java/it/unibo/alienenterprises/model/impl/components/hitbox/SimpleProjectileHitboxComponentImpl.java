package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

public class SimpleProjectileHitboxComponentImpl extends CircleHitboxComponentAbs {

    public SimpleProjectileHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PLAYER || 
            object.getComponent(HitboxComponent.class).get().getType() == Type.ENEMY) {
            object.hit(super.getGameObject().getStatValue(Statistic.DAMAGE));
        }
    }
    
}
