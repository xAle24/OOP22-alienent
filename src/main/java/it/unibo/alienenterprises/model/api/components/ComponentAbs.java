package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * ComponentAbs.
 * Basic implementation of Component
 */
public abstract class ComponentAbs implements Component {

    private final GameObject gameObject;
    private boolean enabled;

    public ComponentAbs(final GameObject object, final boolean enabled) {
        this.gameObject = object;
        this.enabled = enabled;
    }

    @Override
    public void update(final double deltatime) {
        // vuoto
    }

    @Override
    public void start() {
        // vuoto
    }

    @Override
    public void enable() {
        this.enabled = true;
    }

    @Override
    public void disable() {
        this.enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }

    @Override
    public abstract Optional<Component> duplicate(final GameObject obj);

}
