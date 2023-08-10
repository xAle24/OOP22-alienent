package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.EnemyInputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Bomber behaviour.
 */
public class InputComponentBomberImpl extends ComponentAbs implements EnemyInputComponent {
    private GameObject target;
    /**
     * Constructor for input component of bomber.
     * @param object referenced object.
     * @param enabled if component is enabled.
     */
    public InputComponentBomberImpl(final GameObject object, final boolean enabled) {
        super(object, enabled);
    }
    /**
     * @inheritDoc
     */
    @Override
    public void update(final double deltatime) {
        final Vector2D movement = calculateMovement(); 
        getGameObject().setPosition(movement.mul(deltatime).translate(getGameObject().getPosition()));
        getGameObject().setVelocity(movement);
    }

    private Vector2D calculateMovement() {
        return Vector2D.fromTwoPointsAndModule(this.getGameObject().getPosition(), this.target.getPosition(), 
            this.getGameObject().getStatValue(Statistic.SPEED));
    }
    /**
     * @inheritDoc
     */
    @Override
    public void setTarget(final GameObject target) {
        this.target = target;
    }
    /**
     * @inheritDoc
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new InputComponentBomberImpl(obj, isEnabled()));
    }
}
