package it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.hitbox.LineHitboxComponentAbs;

public class LowerBoundaryHitboxComponent extends LineHitboxComponentAbs{

    private static final int DESTROY = 999;

    public LowerBoundaryHitboxComponent(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled, objectType);
    }

    @Override
    public Line2D getLine(Point2D p1, Point2D p2) {
        return new Line2D(p1, p2);
    }

    @Override
    public void isColliding(GameObject object) {
        if (object.getComponent(HitboxComponent.class).get().getType() == Type.PROJECTILE) {
            object.hit(DESTROY);
        } else {
            object.setVelocity(new Vector2D(object.getVelocity().getxComp(), 0));
        }
    }
}
