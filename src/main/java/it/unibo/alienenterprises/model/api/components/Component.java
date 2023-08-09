package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Models a component for a game object.
 */
public interface Component {

    /**
     * The method that will be called every frame.
     * Not all Compoments use a update method.
     * 
     * @param deltatime the time passed between frames in seconds
     */
    void update(double deltatime);

    /**
     * A method that should be called before using a component.
     * Not all components need or use a start method.
     */
    void start();

    /**
     * Set the component as enabled.
     * Not all components can be enabled.
     */
    void enable();

    /**
     * Set the component as disabled.
     * Not all components can be disabled.
     */
    void disable();

    /**
     * @return true if the component is enabled.
     */
    boolean isEnabled();

    /**
     * @return the game object referenced by the component.
     */
    GameObject getGameObject();

    /**
     * Creates a copy of the component referening the given object.
     * 
     * @param obj
     * @return Optional of the component or optional empty if the Component can not
     *         be duplicated or there was an error in the duplication
     */
    Optional<Component> duplicate(GameObject obj);

}
