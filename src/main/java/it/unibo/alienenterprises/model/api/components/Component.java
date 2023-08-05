package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Models a component for a game object.
 */
public interface Component {

    /**
     * @param deltatime
     */
    void update(double deltatime);

    /**
     * 
     */
    void start();

    /**
     * 
     */
    void enable();

    /**
     * 
     */
    void disable();

    /**
     * @return
     */
    boolean isEnabled();

    /**
     * @return
     */
    GameObject getGameObject();

    /**
     * Creates a copy of the component referening the given object
     * 
     * @param obj
     * @return Optional of the component or optional empty if the Component can not
     *         be duplicated or there was an error in the duplication
     */
    Optional<Component> duplicate(GameObject obj);

}
