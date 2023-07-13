package it.unibo.alienenterprises.model.impl.components;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.InputComponent;

public class StraightInputComponent extends ComponentAbs implements InputComponent {

    public StraightInputComponent(final GameObject object, final boolean enabled) {
        super(object, enabled);
    }

    @Override
    public void update(final double deltaTime) {
        getGameObject().setPosition(getGameObject().getVelocity().mul(deltaTime).translate(getGameObject().getPosition()));
    }
    
}
