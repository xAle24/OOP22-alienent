package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.geometry.Geometry2D.Circle2D;

/**
 * Hitbox component interface.
 */
public interface HitboxComponent extends Component {

    Circle2D getCircle2D();

    void collide();
}
