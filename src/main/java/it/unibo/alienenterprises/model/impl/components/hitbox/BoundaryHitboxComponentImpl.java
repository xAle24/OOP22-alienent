package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Line2D;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

public class BoundaryHitboxComponentImpl extends ComponentAbs implements BoundaryHitboxComponent{

    private static final int DESTROY = 999;
    private Type objectType;
    private Line2D line;
    private Locations location;

    public BoundaryHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, Point2D p1, Point2D p2) {
        super(object, enabled);
        this.objectType = objectType;
        this.line = Line2D.fromTwoPoints(p1, p2);
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

    @Override
    public Type getType() {
        return this.objectType;
    }

    @Override
    public void canCollide(HitboxComponent hitbox) {
        if(this.objectType != hitbox.getType()) {
            if (hitbox instanceof CircleHitboxComponentAbs && ((CircleHitboxComponentAbs)hitbox).getHitbox().intersectWhith(this.line)) {
                this.isColliding(hitbox);
            }
        }
    }

    @Override
    public Line2D getLine() {
        return this.line;
    }
}
