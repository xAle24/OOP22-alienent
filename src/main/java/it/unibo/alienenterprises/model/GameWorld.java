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

public final class GameWorld implements World {
    private final CollisionHandler collisionHandler;
    private Set<GameObject> gameObjects;
    private Set<GameObject> deadGameObjects;
    private Set<GameObject> lastAdded;
    private int score;
    private Dimensions worldDimensions;
    private boolean rendered = false;

    public GameWorld(Dimensions worldDimensions) {
        this.worldDimensions = worldDimensions;
        this.collisionHandler = new SimpleCollisionHandler();
        this.gameObjects = new HashSet<>();
        this.deadGameObjects = new HashSet<>();
        this.lastAdded = new HashSet<>();
    }

    @Override
    public void update(double deltaTime) {
        if (this.rendered) {
            this.lastAdded.clear();
        }
        this.gameObjects.stream().forEach(o -> {
            if (!o.isAlive()) {
                this.deadGameObjects.add(o);
            } else {
                o.update(deltaTime);
            }
        });
        this.deadGameObjects.forEach(o -> {
            this.removeGameObject(o);
            this.score += o.getStatValue(Statistic.DAMAGE) * 100;
        });
        this.deadGameObjects.clear();
        this.collisionHandler.checkCollisions();
    }

    @Override
    public void addGameObject(GameObject add) {
        this.gameObjects.add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
        this.rendered = false;
    }

    public void addAllGameObjects(GameObject... objects) {
        this.gameObjects.addAll(List.of(objects));
        this.lastAdded.addAll(List.of(objects));
        this.rendered = false;
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
        this.rendered = true;
        return this.lastAdded;
    }

    /**
     * Removes a GameObject from the list of gameobjects at play.
     * 
     * @param remove the GameObject that needs to be removed.
     */
    private void removeGameObject(GameObject remove) {
        this.gameObjects.remove(remove);
        this.collisionHandler.removeHitbox(remove.getComponent(HitboxComponent.class));
    }
}
