package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;
/**
 * Implementations of all circle hitbox common methods.
 */
public abstract class CircleHitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType; 
    private Circle2D hitbox;
    private double radius;
    /**
     * Constructor for all circle hitbox.
     * @param object referenced object.
     * @param enabled if component is enabled.
     * @param objectType what type of object is.
     * @param radius how long is the circle radius.
     */
    public CircleHitboxComponentAbs(final GameObject object, final boolean enabled, final Type objectType, final double radius) {
        super(object, enabled);
        this.objectType = objectType;
        this.radius = radius;
        this.hitbox = new Circle2D(object.getPosition(), radius);
    }
    /**
     * Returns the hitbox.
     * @return Circle2D of the hitbox.
     */
    public Circle2D getHitbox() {
        return hitbox;
    }
    /**
     * Set the position of the hitbox.
     */
    public void setPosition() {
        this.hitbox = new Circle2D(super.getGameObject().getPosition(), radius);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void update(final double deltatime) {
        setPosition();
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
                    && this.hitbox.intersectWhith(((CircleHitboxComponentAbs) hitbox).getHitbox())) {
                this.isColliding(hitbox);
                hitbox.isColliding(this);
            } else if (hitbox instanceof BoundaryHitboxComponentImpl 
                    && this.hitbox.intersectWhith(((BoundaryHitboxComponentImpl) hitbox).getLine())) {
                hitbox.isColliding(this);
            }
        }
    }
    /**
     * @inheritDoc
     */
    @Override
    public abstract void isColliding(HitboxComponent hitbox);
    /**
     * @inheritDoc
     */
    @Override
    public abstract Optional<Component> duplicate(GameObject obj);
}
