package it.unibo.alienenterprises.model.collisionhandler;

import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Circle2D;

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
    public SimpleCollisionHandler() {
        super();
    }

    @Override
    protected void checkPair(HitboxComponent a, HitboxComponent b) {
        if (a.canCollide(b.getType())) {
            // a.isColliding(b);
            System.out.println("AAAAAAAAAa");
        }
    }
}
