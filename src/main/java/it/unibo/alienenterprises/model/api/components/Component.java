package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * Models a component for a game object.
 */
public interface Component {
    /**
     * Execute the component istruction on the given object.
     * 
     * @param object
     */
    void handle(GameObject object);
}
