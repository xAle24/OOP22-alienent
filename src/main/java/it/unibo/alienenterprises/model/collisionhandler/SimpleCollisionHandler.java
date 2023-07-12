package it.unibo.alienenterprises.model.collisionhandler;

import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

/**
 * Checks collisions among {@link GameObject} instances where
 * the hitbox is a {@link Circle2D} circle.
 */
public final class SimpleCollisionHandler extends CollisionHandlerAbs {

    /**
     * Creates a new {@link SimpleCollisionHandler} instance.
     * 
     * @param collidables the list of collidables to be checked.
     */
    public SimpleCollisionHandler(List<GameObject> collidables) {
        super(collidables);
    }

    /**
     * Checks if the two {@link GameObject} instances are colliding.
     * 
     * @param a the first object.
     * @param b the second object.
     */
    protected void checkPair(GameObject a, GameObject b) {
        HitboxComponent hitboxA = a.getComponent(HitboxComponent.class).get();
        HitboxComponent hitboxB = b.getComponent(HitboxComponent.class).get();
        if (hitboxA.canCollide(hitboxB.getType()) && a.isAlive() && b.isAlive()) {
            if (hitboxA.getCircle2D().intersectWhith(hitboxB.getCircle2D())) {
                hitboxA.Update(a);
                hitboxB.Update(b);
            }
        }
    }
}
