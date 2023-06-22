package it.unibo.alienenterprises.model;

import java.util.Random;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * Bomber behaviour.
 */
public class InputComponentBomberImpl implements InputComponent {
    private static final int RIGHTANGLE = 90;
    private static final int RANDOMANGLE = 91;
    private static final Random rnd = new Random();

    private Vector2D movement = new Vector2D(0, 0);
    private boolean right, up = false;

    @Override
    public void handle(final GameObject object) {
        object.setPosition(movement.translate(object.getPosition()));
        object.setVelocity(movement);
    }

    @Override
    public void calculateMovement(final GameObject player, final GameObject enemy) {
        //movement on right/left direction
        if (enemy.getPosition().getX() > player.getPosition().getX()) {
            right = false;
        } else {
            right = true;
        }

        //movement on up/down direction
        if (enemy.getPosition().getY() > player.getPosition().getY()) {
            up = false;
        } else {
            up = true;
        }

         if (right && up) {
            movement = new Vector2D(rnd.nextInt(RANDOMANGLE), 0);
        } else if (!right && up) {
            movement = new Vector2D(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE, 0);
        } else if (!right && !up) {
            movement = new Vector2D(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE * 2, 0);
        } else {
            movement = new Vector2D(rnd.nextInt(RANDOMANGLE) + RIGHTANGLE * 3, 0);
        }
    }
}
