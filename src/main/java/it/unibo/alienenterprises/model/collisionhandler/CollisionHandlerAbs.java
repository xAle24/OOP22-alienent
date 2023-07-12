package it.unibo.alienenterprises.model.collisionhandler;

import java.util.List;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;

public abstract class CollisionHandlerAbs implements CollisionHandler {
    private List<GameObject> collidables;

    protected CollisionHandlerAbs(List<GameObject> collidables) {
        this.collidables = collidables;
    }

    @Override
    public void checkCollisions() {
        this.collidables.forEach(e -> this.collidables.forEach(o -> checkPair(e, o)));
    }

    protected abstract void checkPair(GameObject a, GameObject b);

    @Override
    public void addGameObject(GameObject toAdd) {
        if (!toAdd.getComponent(HitboxComponent.class).isEmpty()) {
            this.collidables.add(toAdd);
        }
    }

    @Override
    public void removeGameObject(GameObject toRemove) {
        this.collidables.remove(toRemove);
    }

}
