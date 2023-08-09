package it.unibo.alienenterprises.model.collisionhandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.components.HitboxComponent;

/**
 * Generic implementation of the {@link CollisionHandler} interface.
 * 
 * @author Giulia Bonifazi
 */
public abstract class CollisionHandlerAbs implements CollisionHandler {
    private List<HitboxComponent> collidables;

    /**
     * Creates a new instance of this class.
     */
    protected CollisionHandlerAbs() {
        this.collidables = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCollisions() {
        recCheck(collidables, 0);
    }

    /**
     * {@inheritDoc}
     * Recursively checks if the {@link HitboxComponents} are colliding.
     * 
     * @param list the list of game objects.
     * @param num  the number of elements to skip to find the first one of the
     *             current cycle.
     */
    private void recCheck(final List<HitboxComponent> list, final int num) {
        if (num > list.size() - 1) {
            return;
        } else {
            var e = list.stream().skip(num).findFirst().get();
            list.stream().skip(num + 1).forEach(o -> checkPair(o, e));
            recCheck(list, num + 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addHitbox(final Optional<HitboxComponent> toAdd) {
        if (!toAdd.isEmpty()) {
            this.collidables.add(toAdd.get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeHitbox(final Optional<HitboxComponent> toRemove) {
        if (!toRemove.isEmpty()) {
            this.collidables.remove(toRemove.get());
        }
    }

    /**
     * Checks if a pair of {@link HitboxComponent} instances are colliding.
     * 
     * @param a first component.
     * @param b second component.
     */
    protected abstract void checkPair(HitboxComponent a, HitboxComponent b);

}
