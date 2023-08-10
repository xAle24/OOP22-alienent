package it.unibo.alienenterprises.model.collisionhandler;

import it.unibo.alienenterprises.model.api.components.HitboxComponent;

/**
 * Checks collisions among {@link GameObject} instances where
 * the hitbox is a {@link Circle2D} circle.
 * 
 * @author Giulia Bonifazi
 */
public final class SimpleCollisionHandler extends CollisionHandlerAbs {

    @Override
    protected void checkPair(final HitboxComponent a, final HitboxComponent b) {
        a.canCollide(b);
        b.canCollide(a);
    }
}
