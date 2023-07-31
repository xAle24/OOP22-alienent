package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Line2D;
import it.unibo.alienenterprises.model.geometry.Point2D;

public abstract class LineHitboxComponentAbs extends ComponentAbs implements HitboxComponent {

    private Type objectType;
    private Line2D line;

    public LineHitboxComponentAbs(GameObject object, boolean enabled, Type objectType, Point2D p1, Point2D p2) {
        super(object, enabled);
        this.objectType = objectType;
        this.line = Line2D.fromTwoPoints(p1, p2);
    }

    public Line2D getHitbox(Point2D p1, Point2D p2){
        return line;
    }

    @Override
    public Type getType() {
        return this.objectType;
    }
    @Override
    public void update(double deltatime){
        
    }
    @Override
    public Boolean canCollide(Type type) {
        return this.objectType == type ? false : true;
    }
    public void setPosition(Point2D p1, Point2D p2) {
        this.line = Line2D.fromTwoPoints(p1, p2);
    }
    @Override
    public abstract void isColliding(GameObject object);
}