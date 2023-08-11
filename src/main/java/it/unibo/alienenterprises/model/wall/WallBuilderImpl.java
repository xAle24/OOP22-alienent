package it.unibo.alienenterprises.model.wall;

import java.util.HashMap;
import java.util.Map;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent;
import it.unibo.alienenterprises.model.api.components.BoundaryHitboxComponent.Locations;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.GameObjectImpl;
import it.unibo.alienenterprises.model.impl.components.hitbox.BoundaryHitboxComponentImpl;

/**
 * Simple implementation of the {@link WallBuilder} interface.
 * 
 * @author Giulia Bonifazi
 */
public class WallBuilderImpl implements WallBuilder {
    private static final String PLACEHOLDERID = "Mexico_border";
    private static final Type TYPE = Type.BOUNDARY;
    private static final Map<Statistic, Integer> PLACEHOLDERSTATS = new HashMap<>(Map.of(Statistic.HP, 9_999_999));
    private GameObject obj;
    private BoundaryHitboxComponent hitbox;

    /**
     * Creates new instance of this class.
     */
    public WallBuilderImpl() {
        this.obj = new GameObjectImpl(Point2D.ORIGIN, Vector2D.NULL_VECTOR, PLACEHOLDERSTATS, PLACEHOLDERID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPresetGameObject() {
        this.obj = new GameObjectImpl(Point2D.ORIGIN, Vector2D.NULL_VECTOR, PLACEHOLDERSTATS, PLACEHOLDERID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGameObject(final Point2D pos, final Vector2D vect, final Map<Statistic, Integer> stats,
            final String id) {
        var statsRequested = stats;
        var idRequested = id;
        if (statsRequested == null) {
            statsRequested = PLACEHOLDERSTATS;
        }
        if (idRequested == null) {
            idRequested = PLACEHOLDERID;
        }
        this.obj = new GameObjectImpl(pos, vect, statsRequested, idRequested);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBoundaryHitboxComponent(final Point2D p1, final Point2D p2) {
        this.hitbox = new BoundaryHitboxComponentImpl(this.obj, true, TYPE, p1, p2);
        this.obj.addComponent(this.hitbox);
        this.hitbox.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D position) {
        if (position == null) {
            this.obj.setPosition(Point2D.ORIGIN);
        } else {
            this.obj.setPosition(position);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(final Locations location) {
        this.hitbox.setLocations(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getWall() {
        return this.obj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.addPresetGameObject();
        this.hitbox = null;
    }
}
