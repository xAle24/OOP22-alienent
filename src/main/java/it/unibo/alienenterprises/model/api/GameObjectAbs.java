package it.unibo.alienenterprises.model.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Implements methods common to all objects.
 */
public class GameObjectAbs implements GameObject {
    private Point2D position;
    private Vector2D velocity;
    private int health;
    private Map<Statistic, Integer> stats; 
    private Set<Component> component;
    private String id;
    private double recoveryCooldown;
    /**
     * Contructor for all objects.
     * @param pos Point2D of the position of the object.
     * @param vector Vector2D of the position of the object
     * @param stat a map with all the basic statistics of the object.
     * @param id the string id of the object.
     */
    public GameObjectAbs(final Point2D pos, final Vector2D vector, final Map<Statistic, Integer> stat, final String id) {
        this.position = pos;
        this.velocity = vector;
        this.stats = stat;
        this.health = stat.get(Statistic.HP);
        this.id = id;
        this.component = new HashSet<Component>();
    }
    /**
     * @inheritDoc
     */
    @Override
    public boolean isAlive() {
        return this.gethealth() > 0;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Point2D getPosition() {
        return position;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setPosition(final Point2D point) {
        position = point;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Vector2D getVelocity() {
        return velocity;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setVelocity(final Vector2D vector) {
        velocity = vector;
    }
    /**
     * @inheritDoc
     */
    @Override
    public List<Component> getAllComponent() {
        return component.stream().toList();
    }
    /**
     * @inheritDoc
     */
    @Override
    public int getStatValue(final Statistic stat) {
        return stats.get(stat);
    }
    /**
     * @inheritDoc
     */
    @Override
    public Map<Statistic, Integer> getAllStats() {
        return new HashMap<Statistic, Integer>(stats);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setStatValue(final Statistic stat, final int value) {
        stats.put(stat, value);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void addComponent(final Component component) {
        this.component.add(component);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void hit(final int dmg) {
        this.health = this.health - dmg;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void heal(final int heal) {
        this.health = this.health + heal;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void update(final double deltatime) {
        getAllComponent().stream().forEach(e -> e.update(deltatime));
        if (this.recoveryCooldown > 1){
            this.heal(stats.get(Statistic.RECOVERY));
            this.recoveryCooldown = 0;
        } else {
            this.recoveryCooldown += deltatime; 
        }
    }
    /**
     * @inheritDoc
     */
    @Override
    public int gethealth() {
        return this.health;
    }
    /**
     * @inheritDoc
     */
    @Override
    public String getId() {
        return id;
    }
    /**
     * @inheritDoc
     */
    @Override
    public void addAllComponent(final Collection<Component> components) {
        this.component.addAll(components);
    }
}
