package it.unibo.alienenterprises.model.impl.components;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.InputComponent;

public class StraightForwardInputComponent implements InputComponent {

    @Override
    public void handle(final GameObject object) {
        object.setPosition(object.getVelocity().translate(object.getPosition()));
    }

    @Override
    public void calculateMovement(GameObject player, GameObject enemy) {
        // TODO Auto-generated method stub
        // da rimuovere
        throw new UnsupportedOperationException("Unimplemented method 'calculateMovement'");
    }
    
}
