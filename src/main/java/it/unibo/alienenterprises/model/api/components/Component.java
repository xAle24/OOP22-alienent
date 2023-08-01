package it.unibo.alienenterprises.model.api.components;

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
    
}
