package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.InputComponent;

/**
 * StraightInputComponent.
 * An InputComponent that goes in a straight line
 */
public class StraightInputComponent extends ComponentAbs implements InputComponent {

    /**
     * set Up a StraightInputComponent
     * @param object
     * @param enabled
     */
    public StraightInputComponent(final GameObject object, final boolean enabled) {
        super(object, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltaTime) {
        getGameObject()
                .setPosition(getGameObject().getVelocity().mul(deltaTime).translate(getGameObject().getPosition()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new StraightInputComponent(obj, isEnabled()));
    }
}
