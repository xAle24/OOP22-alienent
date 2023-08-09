package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.Dimensions;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.collisionhandler.CollisionHandler;
import it.unibo.alienenterprises.model.collisionhandler.SimpleCollisionHandler;
import it.unibo.alienenterprises.model.util.DoubleBuffer;
import it.unibo.alienenterprises.model.util.SetDoubleBuffer;

/**
 * Main World of the game. This is where all the objects reside.
 * 
 * @author Giulia Bonifazi
 */
public final class GameWorld implements World {
    private final CollisionHandler collisionHandler;
    private final DoubleBuffer<GameObject> doubleBuff;
    private final Set<GameObject> lastAdded;
    private GameObject player;
    private int score;
    private Dimensions worldDimensions;

    /**
     * Creates new instance of this class.
     * 
     * @param worldDimensions the dimensions of the game world; these are fixed and
     *                        cannot be changed.
     */
    public GameWorld(final Dimensions worldDimensions) {
        this.worldDimensions = worldDimensions;
        this.doubleBuff = new SetDoubleBuffer<>();
        this.collisionHandler = new SimpleCollisionHandler();
        this.lastAdded = new HashSet<>();
    }

    @Override
    public void update(final double deltaTime) {
        this.score += 1;
        this.doubleBuff.changeBuffer();
        this.doubleBuff.getCurr().stream().forEach(o -> {
            if (!o.isAlive()) {
                this.removeGameObject(o);
            } else {
                o.update(deltaTime);
            }
        });
        this.collisionHandler.checkCollisions();
    }

    @Override
    public void addGameObject(final GameObject add) {
        this.doubleBuff.getBuff().add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
        this.lastAdded.add(add);
    }

    @Override
    public void addAllGameObjects(final GameObject... objects) {
        var newObj = List.of(objects);
        newObj.forEach(o -> this.addGameObject(o));
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Dimensions getWorldDimensions() {
        return this.worldDimensions;
    }

    @Override
    public Set<GameObject> getLastAdded() {
        var ret = new HashSet<>(this.lastAdded);
        this.lastAdded.clear();
        return ret;
    }

    @Override
    public void addPlayer(final GameObject player) {
        this.player = player;
        this.addGameObject(player);
    }

    @Override
    public boolean isOver() {
        return !this.player.isAlive();
    }

    /**
     * Removes a GameObject from the list of gameobjects at play.
     * 
     * @param remove the GameObject that needs to be removed.
     */
    private void removeGameObject(final GameObject remove) {
        this.score += remove.getStatValue(Statistic.DAMAGE) * 100;
        this.doubleBuff.getBuff().remove(remove);
        this.collisionHandler.removeHitbox(remove.getComponent(HitboxComponent.class));
    }
}
