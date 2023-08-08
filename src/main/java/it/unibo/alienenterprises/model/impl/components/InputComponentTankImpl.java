package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.EnemyInputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Tank behaviour.
 */
public class InputComponentTankImpl extends ComponentAbs implements EnemyInputComponent {
    private static final int DISTANCE = 300;
    private GameObject target;

    public InputComponentTankImpl(final GameObject object, final boolean enabled) {
        super(object, enabled);
    }

    @Override
    public void update(final double deltatime) {
        final Vector2D movement = calculateMovement(); 
        getGameObject().setPosition(movement.mul(deltatime).translate(getGameObject().getPosition()));
        getGameObject().setVelocity(movement);
    }

    private Vector2D calculateMovement() {
        double distanceTarget = Math.sqrt(Math.pow(this.target.getPosition().getX() - this.getGameObject().getPosition().getX(), 2) + 
                            Math.pow(this.target.getPosition().getY() - this.getGameObject().getPosition().getY(), 2));
        if (distanceTarget < DISTANCE){
            return Vector2D.fromTwoPointsAndModule(this.getGameObject().getPosition(), this.target.getPosition(), 0);
        } else {
            return Vector2D.fromTwoPointsAndModule(this.getGameObject().getPosition(), this.target.getPosition(), 
                this.getGameObject().getStatValue(Statistic.SPEED));
        }
    }
    public void setTarget(final GameObject target) {
        this.target = target;
    }
    @Override
    public Optional<Component> duplicate(GameObject obj) {
        return Optional.of(new InputComponentTankImpl(obj, isEnabled()));
    }
}
