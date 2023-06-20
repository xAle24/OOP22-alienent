package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Geometry2D;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

public class InputComponentSniperImpl implements InputComponent {

    Vector2D movement = new Vector2D(0, 0);

    @Override
    public void handle(GameObject object) {
        object.setPosition(Geometry2D.traslate(object.getPosition(), movement));
        object.setVelocity(movement);
    }
    
    public void calculateMovement(GameObject player, GameObject enemy) {
        player.getPosition();
        
    }
}
