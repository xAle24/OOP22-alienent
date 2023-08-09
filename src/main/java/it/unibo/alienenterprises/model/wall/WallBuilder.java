package it.unibo.alienenterprises.model.wall;

import java.util.Map;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent.Locations;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Builds a the enclosing walls.
 * 
 * @author Giulia Bonifazi
 */
public interface WallBuilder {

    /**
     * Initializes a preset {@link GameObject} with preset values.
     * The object is unmoving and lacks any component; the only set
     * {@link Statistic} is health.
     */
    void addPresetGameObject();

    /**
     * Adds a non-preset {@link GameObject}. If stats or ID is null, it will
     * initialize them to the preset values.
     * 
     * @param pos   the position
     * @param vect  the velocity
     * @param stats the statistics
     * @param ID    the ID of the object
     */
    void addGameObject(Point2D pos, Vector2D vect, Map<Statistic, Integer> stats, String ID);

    /**
     * Adds a {@link BoundaryHitboxComponent} to the Wall. The component is
     * automatically enabled and it is automatically considered a boundary.
     * 
     * @param p1 first point the hitbox line has to cross
     * @param p2 second point the hitbox line has to cross
     */
    void addBoundaryHitboxComponent(Point2D p1, Point2D p2);

    /**
     * Sets a new position to the gameObject.
     * 
     * @param position the new position.
     */
    void setPosition(Point2D position);

    /**
     * Sets the {@link BoundaryHitboxComponent} location. It will determine how it
     * reacts to collisions.
     * 
     * @param location the location.
     */
    void setLocation(Locations location);

    /**
     * Gets the Wall that has been built.
     * 
     * @return the wall.
     */
    GameObject getWall();

    /**
     * Resets the builder to the starting condition.
     */
    void clear();
}
