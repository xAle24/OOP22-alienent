package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Line2D;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
/**
 * Implementations of buondary hitbox.
 */
public class BoundaryHitboxComponentImpl extends ComponentAbs implements BoundaryHitboxComponent {

    private static final int DESTROY = 999;
    private Type objectType;
    private Line2D line;
    private Locations location;
    /**
     * Constructor for boundary hitbox.
     * @param object referenced object.
     * @param enabled if component is enabled.
     * @param objectType what type of object is.
     * @param p1 where line starts.
     * @param p2 where line ends.
     */
    public BoundaryHitboxComponentImpl(final GameObject object, final boolean enabled, final Type objectType, 
            final Point2D p1, final Point2D p2) {
        super(object, enabled);
        this.objectType = objectType;
        this.line = Line2D.fromTwoPoints(p1, p2);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void isColliding(final HitboxComponent hitbox) {
        if (hitbox.getType() == Type.PROJECTILE) {
            hitbox.getGameObject().hit(DESTROY);
        } else if (this.location == Locations.UP) {
            hitbox.getGameObject().setPosition(Vector2D.fromComponents(0, 
            (((CircleHitboxComponentAbs) hitbox).getHitbox().getRay() - line.distancePoint(hitbox.getGameObject().getPosition())))
            .translate(hitbox.getGameObject().getPosition()));
        } else if (this.location == Locations.DOWN) {
            hitbox.getGameObject().setPosition(Vector2D.fromComponents(0, 
            - (((CircleHitboxComponentAbs) hitbox).getHitbox().getRay() - line.distancePoint(hitbox.getGameObject().getPosition())))
            .translate(hitbox.getGameObject().getPosition()));
        } else if (this.location == Locations.RIGHT) {
            hitbox.getGameObject().setPosition(Vector2D.fromComponents( 
            - (((CircleHitboxComponentAbs) hitbox).getHitbox().getRay() - line.distancePoint(hitbox.getGameObject().getPosition())), 0)
            .translate(hitbox.getGameObject().getPosition()));
        } else if (this.location == Locations.LEFT) {
            hitbox.getGameObject().setPosition(Vector2D.fromComponents( 
            (((CircleHitboxComponentAbs) hitbox).getHitbox().getRay() - line.distancePoint(hitbox.getGameObject().getPosition())), 0)
            .translate(hitbox.getGameObject().getPosition()));
        }
    }
    /**
     * Set the locations of the boundary.
     */
    @Override
    public void setLocations(final Locations location) {
        this.location = location;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Type getType() {
        return this.objectType;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void canCollide(final HitboxComponent hitbox) {
        if (this.objectType != hitbox.getType()) {
            if (hitbox instanceof CircleHitboxComponentAbs 
                    && ((CircleHitboxComponentAbs) hitbox).getHitbox().intersectWhith(this.line)) {
                this.isColliding(hitbox);
            }
        }
    }
    /**
     * Returns the line of the boundary.
     */
    @Override
    public Line2D getLine() {
        return this.line;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.empty();
    }
}
