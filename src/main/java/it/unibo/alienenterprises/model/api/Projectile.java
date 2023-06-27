package it.unibo.alienenterprises.model.api;

import it.unibo.alienenterprises.model.geometry.Point2D;

/**
 * Projectile
 */
public interface Projectile extends GameObject {

    /**
     * return a projectile with the same components but modified damage, position
     * and angle
     * 
     * @param damage
     * @param position
     * @param angle
     * @return
     */
    Projectile spawn(final int damage, final int maxSpeed, final Point2D position, final double angle);
}
