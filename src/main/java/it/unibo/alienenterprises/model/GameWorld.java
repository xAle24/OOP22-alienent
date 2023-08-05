package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.World;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.collisionhandler.CollisionHandler;
import it.unibo.alienenterprises.model.collisionhandler.SimpleCollisionHandler;

public class GameWorld implements World {
    private final CollisionHandler collisionHandler;
    private Set<GameObject> gameObjects;

    public GameWorld() {
        this.collisionHandler = new SimpleCollisionHandler();
        this.gameObjects = new HashSet<>();
        // this.collisionHandler.addAll();
    }

    @Override
    public void update(double deltaTime) {
        this.gameObjects.stream().forEach(o -> o.update(deltaTime));
        this.collisionHandler.checkCollisions();
    }

    @Override
    public void addGameObject(GameObject add) {
        this.gameObjects.add(add);
        this.collisionHandler.addHitbox(add.getComponent(HitboxComponent.class));
    }

    @Override
    public void removeGameObject(GameObject remove) {
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
