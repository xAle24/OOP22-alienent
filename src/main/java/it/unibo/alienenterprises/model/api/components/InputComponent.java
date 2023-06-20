package it.unibo.alienenterprises.model.api.components;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

/**
 * InputComponent
 */
public interface InputComponent extends Component {

    Vector2D calculateMovement(GameObject player, GameObject enemy);
    
}
