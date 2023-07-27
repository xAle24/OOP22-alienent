package it.unibo.alienenterprises.model.impl.components;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public class HitboxComponentImpl extends ComponentAbs implements HitboxComponent {

    private Type objectType; 
    private Circle2D hitbox;

    public HitboxComponentImpl(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled);
        this.objectType = objectType;
        this.hitbox = getCircle2D();
    }

    @Override
    public Circle2D getCircle2D() {
        return new Circle2D(super.getGameObject().getPosition(), 4);
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
    public Void isColliding() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isColliding'");
    }
    
}
