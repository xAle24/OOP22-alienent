package it.unibo.alienenterprises.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Implements methods common to all objects.
 */
public abstract class GameObjectAbs implements GameObject {
    private Point2D position;
    private Vector2D velocity;
    private final Map<Statistic, Integer> stats; 
    private final InputComponent input;
    private final HitboxComponent hitbox;

    /**
     * Contructor for all objects.
     * @param pos
     * @param vector
     * @param hitbox
     * @param input
     * @param stat
     */
    public GameObjectAbs(final Point2D pos, final Vector2D vector, final HitboxComponent hitbox, 
                        final InputComponent input, final Map<Statistic, Integer> stat) {
        this.position = pos;
        this.velocity = vector;
        this.input = input;
        this.hitbox = hitbox;
        this.stats = stat;
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
        List<Component> component = new ArrayList<>();
        component.add(hitbox);
        component.add(input);
        return component;
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

    // da non pushare
    protected Map<Statistic,Integer> getStats(){
        return stats;
    }
}
