package it.unibo.alienenterprises.model;

import java.util.Random;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Geometry2D;
import it.unibo.alienenterprises.model.geometry.Geometry2D.Vector2D;

/**
 * Tank behaviour.
 */
public class InputComponentTankImpl implements InputComponent {
    private static final int DISTANCE = 300;
    private static final int RIGHTANGLE = 90;
    private static final int RANDOMANGLE = 91;
    private static final Random rnd = new Random();

    private Vector2D movement = new Vector2D(0, 0);
    private boolean right, up = false;

    @Override
    public void handle(final GameObject object) {
        object.setPosition(Geometry2D.traslate(object.getPosition(), movement));
        object.setVelocity(movement);
    }

    @Override
    public void calculateMovement(final GameObject player, final GameObject enemy) {
        //movement on right/left direction
        if (enemy.getPosition().x() > player.getPosition().x() 
        &&  Math.abs(enemy.getPosition().x() - player.getPosition().x()) > DISTANCE) {
            right = true;
        } else if (enemy.getPosition().x() > player.getPosition().x()
        &&  Math.abs(enemy.getPosition().x() - player.getPosition().x()) < DISTANCE) {
            right = false;
        } else if (enemy.getPosition().x() < player.getPosition().x() 
        &&  Math.abs(enemy.getPosition().x() - player.getPosition().x()) < DISTANCE) {
            right = true;
        } else {
            right = false;
        }

        //movement on up/down direction
        if (enemy.getPosition().y() > player.getPosition().y() 
        && Math.abs(enemy.getPosition().y() - player.getPosition().y()) > DISTANCE) {
            up = true;
        } else if (enemy.getPosition().y() > player.getPosition().y() 
        && Math.abs(enemy.getPosition().y() - player.getPosition().y()) < DISTANCE) {
            up = false;
        } else if (enemy.getPosition().y() < player.getPosition().y() 
        && Math.abs(enemy.getPosition().y() - player.getPosition().y()) < DISTANCE) {
            up = true;
        } else {
            up = false;
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
