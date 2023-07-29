package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

public abstract class HitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType; 

    public HitboxComponentAbs(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled);
        this.objectType = objectType;
    }

    public abstract Circle2D getCircle2D();

    @Override
    public Type getType() {
        return this.objectType;
    }

    @Override
    public Boolean canCollide(Type type) {
        return this.objectType == type ? false : true;
    }

    @Override
    public abstract void isColliding(GameObject object);
}
