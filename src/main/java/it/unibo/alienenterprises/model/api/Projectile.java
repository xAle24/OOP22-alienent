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
     * @param position
     * @param angle
     * @return
     */
    Projectile spawn(final Point2D position, final double angle, final int damage, final int speed);
}
