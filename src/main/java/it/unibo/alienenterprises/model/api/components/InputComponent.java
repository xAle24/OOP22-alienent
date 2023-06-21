package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * InputComponent.
 */
public interface InputComponent extends Component {
    /**
     * Calculate the enemis movement in relation to player position. 
     * @param player
     * @param enemy
     */
    void calculateMovement(GameObject player, GameObject enemy);

}
