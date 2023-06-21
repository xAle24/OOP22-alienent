package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;

/**
 * InputComponent
 */
public interface InputComponent extends Component {

    void calculateMovement(GameObject player, GameObject enemy);

}
