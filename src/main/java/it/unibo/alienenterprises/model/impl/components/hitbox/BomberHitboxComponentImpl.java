package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public class BomberHitboxComponentImpl extends HitboxComponentAbs{

    private static final int BOMBERDAMAGE = 999;

    public BomberHitboxComponentImpl(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled, objectType);
    }

    @Override
    public Circle2D getCircle2D() {
        return new Circle2D(super.getGameObject().getPosition(), 10);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PLAYER) {
            object.hit(BOMBERDAMAGE);
        }
    }
    
}
