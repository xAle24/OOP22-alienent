package it.unibo.alienenterprises.model.api;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Implements methods common to all objects.
 */
public abstract class GameObjectAbs implements GameObject {
    private Point2D position;
    private Vector2D velocity;
    private Map<Statistic, Integer> stats; 
    private Set<Component> component;

    /**
     * Contructor for all objects.
     * @param pos
     * @param vector
     * @param stat
     */
    public GameObjectAbs(final Point2D pos, final Vector2D vector, final Map<Statistic, Integer> stat, final Component... components) {
        this.position = pos;
        this.velocity = vector;
        this.stats = stat;
        this.component = new HashSet<>(Set.of(components));
    }

    @Override
    public abstract boolean isAlive();
    /**
     * return the position of the object.
     * @return the object position 
     */
    @Override
    public Point2D getPosition() {
        return position;
    }
    /**
     * set the position of the object.
     * @param point
     */
    @Override
    public void setPosition(final Point2D point) {
        position = point;
    }
    /**
     * return the velocity vector.
     * @return the object velocity vector 
     */
    @Override
    public Vector2D getVelocity() {
        return velocity;
    }
    /**
     * set the velocity of the object.
     * @param vector
     */
    @Override
    public void setVelocity(final Vector2D vector) {
        velocity = vector;
    }
    /**
     * return a list of all components.
     * @return a list with all the objects component
     */
    @Override
    public List<Component> getAllComponent() {
        return component.stream().toList();
    }
    /**
     * Return the input stat value.
     * @param stat
     * @return int of the input stat value
     */
    @Override
    public int getStatValue(final Statistic stat) {
        return stats.get(stat);
    }
    /**
     * Return a map with all stats.
     * @return map of with their stats value 
     */
    @Override
    public Map<Statistic, Integer> getAllStats() {
        return stats;
    }
    /**
     * Insert the given value in the corresponding stat.
     */
    @Override
    public void setStatValue(final Statistic stat, final int value) {
        stats.put(stat, value);
    }
    /**
     * Add a component to the object.
     * @param component
     */
    @Override
    public void addComponent(final Component component) {
        this.component.add(component);
    }
    /**
     * Update the game object.
     */
    @Override
    public void update() {
        //TODO
        //getAllComponent().stream().filter(e -> !e.getClass().equals(HitboxComponent.class)).forEach(e -> e.Update(this));
    }
}
