package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public abstract class CircleHitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType; 
    private Circle2D hitbox;
    private double radius;

    public CircleHitboxComponentAbs(GameObject object, boolean enabled, Type objectType, double radius) {
        super(object, enabled);
        this.objectType = objectType;
        this.radius = radius;
        this.hitbox = new Circle2D(object.getPosition(), radius);
    }

    public Circle2D getHitbox(){
        return hitbox;
    }

    public void setPosition() {
        this.hitbox = new Circle2D(super.getGameObject().getPosition(), radius);
    }
    @Override
    public void update(double deltatime){
        setPosition();
    }
    @Override
    public Type getType() {
        return this.objectType;
    }
    @Override
    public void canCollide(HitboxComponent hitbox) {
        if(this.objectType != hitbox.getType()) {
            if (hitbox instanceof CircleHitboxComponentAbs && this.hitbox.intersectWhith(((CircleHitboxComponentAbs)hitbox).getHitbox())) {
                this.isColliding(hitbox);
                hitbox.isColliding(this);
            } else if (hitbox instanceof BoundaryHitboxComponentImpl && this.hitbox.intersectWhith(((BoundaryHitboxComponentImpl)hitbox).getLine())) {
                hitbox.isColliding(this);
            }
        }
    }
    @Override
    public abstract void isColliding(HitboxComponent hitbox);

    @Override
    public abstract Optional<Component> duplicate(GameObject obj);
}
