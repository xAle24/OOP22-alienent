package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;
import it.unibo.alienenterprises.model.geometry.Point2D;

public abstract class LineHitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType; 

    public LineHitboxComponentAbs(GameObject object, boolean enabled, Type objectType) {
        super(object, enabled);
        this.objectType = objectType;
    }

    public abstract Line2D getLine(Point2D p1, Point2D p2);

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
