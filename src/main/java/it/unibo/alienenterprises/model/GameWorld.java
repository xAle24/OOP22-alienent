package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.collisionhandler.CollisionHandler;
import it.unibo.alienenterprises.model.collisionhandler.SimpleCollisionHandler;

public final class GameWorld implements World {
    private final CollisionHandler collisionHandler;
    private Set<GameObject> gameObjects;
    private Set<GameObject> deadGameObjects;

    public GameWorld() {
        this.collisionHandler = new SimpleCollisionHandler();
        this.gameObjects = new HashSet<>();
        this.deadGameObjects = new HashSet<>();
    }

    @Override
    public void update(double deltaTime) {
        this.gameObjects.stream().forEach(o -> {
            if (!o.isAlive()) {
                this.deadGameObjects.add(o);
            } else {
                o.update(deltaTime);
            }
        });
        this.deadGameObjects.forEach(o -> this.removeGameObject(o));
        this.deadGameObjects.clear();
        this.collisionHandler.checkCollisions();
    }

    @Override
    public void addGameObject(GameObject add) {
        this.gameObjects.add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
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

    public void addAllGameObjects(GameObject... objects) {
        this.gameObjects.addAll(List.of(objects));
    }

    @Override
    public void notifyScore(int score) {

    }

}
