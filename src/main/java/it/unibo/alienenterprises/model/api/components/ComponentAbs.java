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

    /**
     * Set up the base for a component.
     * 
     * @param object  the object that will be attached to the component
     * @param enabled the initial state of the component
     *                ( true = enabled, false = disabled)
     */
    public ComponentAbs(final GameObject object, final boolean enabled) {
        this.gameObject = object;
        this.enabled = enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltatime) {
        /*
         * This method is empty on purpose,
         * so that not all Components must impement it
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        /*
         * This method is empty on purpose,
         * so that not all Components must impement it
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enable() {
        this.enabled = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable() {
        this.enabled = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Optional<Component> duplicate(GameObject obj);

}
