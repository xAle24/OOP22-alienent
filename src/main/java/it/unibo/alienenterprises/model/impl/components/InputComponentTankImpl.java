package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;
import java.util.Random;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Tank behaviour.
 */
public class InputComponentTankImpl extends ComponentAbs implements InputComponent {
    private static final int DISTANCE = 300;
    private static final int RIGHTANGLE = 90;
    private static final int RANDOMANGLE = 91;
    private static final Random rnd = new Random();

    private boolean right, up = false;
    private GameObject target;

    public InputComponentTankImpl(final GameObject object,final boolean enabled, final GameObject target) {
        super(object, enabled);
        this.target = target;
    }

    @Override
    public void update(final double deltatime) {
        final Vector2D movement = calculateMovement(); 
        getGameObject().setPosition(movement.mul(deltatime).translate(getGameObject().getPosition()));
        getGameObject().setVelocity(movement);
    }

    private Vector2D calculateMovement() {
        //movement on right/left direction
        if (this.getGameObject().getPosition().getX() > target.getPosition().getX() 
        &&  Math.abs(this.getGameObject().getPosition().getX() - target.getPosition().getX()) > DISTANCE) {
            right = true;
        } else if (this.getGameObject().getPosition().getX() > target.getPosition().getX()
        &&  Math.abs(this.getGameObject().getPosition().getX() - target.getPosition().getX()) < DISTANCE) {
            right = false;
        } else if (this.getGameObject().getPosition().getX() < target.getPosition().getX() 
        &&  Math.abs(this.getGameObject().getPosition().getX() - target.getPosition().getX()) < DISTANCE) {
            right = true;
        } else {
            right = false;
        }

        //movement on up/down direction
        if (this.getGameObject().getPosition().getY() > target.getPosition().getY() 
        && Math.abs(this.getGameObject().getPosition().getY() - target.getPosition().getY()) > DISTANCE) {
            up = true;
        } else if (this.getGameObject().getPosition().getY() > target.getPosition().getY() 
        && Math.abs(this.getGameObject().getPosition().getY() - target.getPosition().getY()) < DISTANCE) {
            up = false;
        } else if (this.getGameObject().getPosition().getY() < target.getPosition().getY() 
        && Math.abs(this.getGameObject().getPosition().getY() - target.getPosition().getY()) < DISTANCE) {
            up = true;
        } else {
            up = false;
        }

        if (right && up) {
            return Vector2D.fromAngleAndModule(rnd.nextInt(RANDOMANGLE), 0);
        } else if (!right && up) {
            return Vector2D.fromAngleAndModule(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE, 0);
        } else if (!right && !up) {
            return Vector2D.fromAngleAndModule(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE * 2, 0);
        } else {
            return Vector2D.fromAngleAndModule(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE * 3, 0);
        }
    }

    @Override
    public Optional<Component> duplicate(GameObject obj) {
        return Optional.of(new InputComponentTankImpl(obj, isEnabled(), target));
    }
}
