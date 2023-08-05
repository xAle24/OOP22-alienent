package it.unibo.alienenterprises.model.collisionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.components.HitboxComponent;

public abstract class CollisionHandlerAbs implements CollisionHandler {
    private List<HitboxComponent> collidables;

    protected CollisionHandlerAbs() {
        this.collidables = new ArrayList<>();
    }

    @Override
    public void checkCollisions() {
        recCheck(collidables, 0);
    }

    private void recCheck(List<HitboxComponent> list, int num) {
        if (num > list.size() - 1) {
            return;
        } else {
            var e = list.stream().skip(num).findFirst().get();
            list.stream().skip(num + 1).forEach(o -> checkPair(o, e));
            recCheck(list, num + 1);
        }
    }

    protected abstract void checkPair(HitboxComponent a, HitboxComponent b);

    @Override
    public void addHitbox(Optional<HitboxComponent> toAdd) {
        if (!toAdd.isEmpty()) {
            this.collidables.add(toAdd.get());
        }
    }

    @Override
    public void removeHitbox(Optional<HitboxComponent> toRemove) {
        if (!toRemove.isEmpty()) {
            this.collidables.remove(toRemove.get());
        }
    }

}
