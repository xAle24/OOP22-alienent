package it.unibo.alienenterprises.model.api;

/**
 * Models a component for a game object.
 */
public interface Component {
    /**
     * Execute the component istruction on the given object
     * @param object
     */
    void handle(GameObject object);
}
