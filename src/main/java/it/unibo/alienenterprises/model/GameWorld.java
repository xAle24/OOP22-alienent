package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.Dimensions;
import it.unibo.alienenterprises.model.api.EnemySpawner;
import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.PlayerSpawner;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.collisionhandler.CollisionHandler;
import it.unibo.alienenterprises.model.collisionhandler.SimpleCollisionHandler;
import it.unibo.alienenterprises.model.util.DoubleBuffer;
import it.unibo.alienenterprises.model.util.SetDoubleBuffer;

public final class GameWorld implements World {
    private final CollisionHandler collisionHandler;
    private final DoubleBuffer<GameObject> doubleBuff;
    private final Set<GameObject> lastAdded;
    private int score;
    private Dimensions worldDimensions;

    public GameWorld(Dimensions worldDimensions) {
        this.worldDimensions = worldDimensions;
        this.doubleBuff = new SetDoubleBuffer<>();
        this.collisionHandler = new SimpleCollisionHandler();
        this.lastAdded = new HashSet<>();
    }

    @Override
    public void update(double deltaTime) {
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
    public void addGameObject(GameObject add) {
        this.doubleBuff.getBuff().add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
        this.lastAdded.add(add);
    }

    public void addAllGameObjects(GameObject... objects) {
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

    /**
     * Removes a GameObject from the list of gameobjects at play.
     * 
     * @param remove the GameObject that needs to be removed.
     */
    private void removeGameObject(GameObject remove) {
        this.score += remove.getStatValue(Statistic.DAMAGE) * 100;
        this.doubleBuff.getBuff().remove(remove);
        this.collisionHandler.removeHitbox(remove.getComponent(HitboxComponent.class));
    }
}
