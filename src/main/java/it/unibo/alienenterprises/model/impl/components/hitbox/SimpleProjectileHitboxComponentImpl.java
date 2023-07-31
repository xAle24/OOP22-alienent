package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public class SimpleProjectileHitboxComponentImpl extends CircleHitboxComponentAbs {

    public SimpleProjectileHitboxComponentImpl(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled, objectType);
    }

    @Override
    public Circle2D getCircle2D() {
        return new Circle2D(super.getGameObject().getPosition(), 1);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PLAYER || 
            object.getComponent(HitboxComponent.class).get().getType() == Type.ENEMY) {
            object.hit(super.getGameObject().getStatValue(Statistic.DAMAGE));
        }
    }
    
}
