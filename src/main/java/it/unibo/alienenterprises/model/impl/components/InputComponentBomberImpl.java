package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;
import java.util.Random;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Bomber behaviour.
 */
public class InputComponentBomberImpl extends ComponentAbs implements InputComponent {
    private static final int RIGHTANGLE = 90;
    private static final int RANDOMANGLE = 91;
    private static final Random rnd = new Random();

    private boolean right, up = false;
    private GameObject target;

    public InputComponentBomberImpl(final GameObject object,final boolean enabled, final GameObject target) {
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
        if (getGameObject().getPosition().getX() > target.getPosition().getX()) {
            right = false;
        } else {
            right = true;
        }

        //movement on up/down direction
        if (getGameObject().getPosition().getY() > target.getPosition().getY()) {
            up = false;
        } else {
            up = true;
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
        return Optional.of(new InputComponentBomberImpl(obj, isEnabled(), target));
    }
}
