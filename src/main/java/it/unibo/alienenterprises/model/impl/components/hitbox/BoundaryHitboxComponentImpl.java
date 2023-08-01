package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

public class BoundaryHitboxComponentImpl extends LineHitboxComponentAbs implements BoundaryHitboxComponent{

    private static final int DESTROY = 999;
    private Locations location;

    public BoundaryHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, Point2D p1, Point2D p2) {
        super(object, enabled, objectType, p1, p2);
    }

    @Override
    public void isColliding(HitboxComponent hitbox) {
        if (hitbox.getType() == Type.PROJECTILE) {
            hitbox.getGameObject().hit(DESTROY);
        } else if((this.location == Locations.UP) || (this.location == Locations.DOWN)) {
            hitbox.getGameObject().setVelocity(new Vector2D(hitbox.getGameObject().getVelocity().getxComp(), 0));
        } else {
            hitbox.getGameObject().setVelocity(new Vector2D(0, hitbox.getGameObject().getVelocity().getyComp()));
        }
    }

    @Override
    public void setLocations(Locations location) {
        this.location = location;
    }
}
