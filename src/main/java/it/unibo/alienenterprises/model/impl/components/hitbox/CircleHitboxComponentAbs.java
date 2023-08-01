package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public abstract class CircleHitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType; 
    private Circle2D hitbox;
    private int radius;

    public CircleHitboxComponentAbs(GameObject object, boolean enabled, Type objectType, int radius) {
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
    public Boolean canCollide(Type type) {
        return this.objectType == type ? false : true;
    }
    @Override
    public abstract void isColliding(HitboxComponent hitbox);
}
