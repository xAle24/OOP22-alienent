package it.unibo.alienenterprises.model;

import java.util.HashSet;
import java.util.Set;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.PlayerInputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;
/**
 * PlayerInputComponent
 * Handle the movements of the player.
 */
public class PlayerInputComponentImpl implements PlayerInputComponent {

    /**
     * Represent the acceleration of the palyer
     */
    private static final double ACC = 1; // valori da definire
    
    /**
     * Represent the angular velocity of the player
     */
    private static final double ANG_VEL = 1; 

    private final Set<Input> inputList = new HashSet<>();

    @Override
    public void Update(final GameObject object) {
        var vel = object.getVelocity();
        final var maxSpeed = object.getStatValue(Statistic.SPEED);
        for (Input in : inputList) {
            final var module = vel.getModule();
            switch (in) {
                case ACCELERATE:
                    if (module < maxSpeed) {
                        if (module + ACC > maxSpeed) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), maxSpeed);
                        }
                        vel = Vector2D.fromAngleAndModule(vel.getAngle(), module + ACC);
                    }
                    break;
                case STOP_ACCELERATE:
                    if (module > 0) {
                        if (module - ACC < 0) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), 0);
                        }
                        vel = Vector2D.fromAngleAndModule(vel.getAngle(), module - ACC);
                    }
                    break;
                case TURN_LEFT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() + (ANG_VEL), module);
                    break;
                case TURN_RIGHT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() - (ANG_VEL), module);
                    break;
                default:
                    break;
            }
        }

        this.inputList.clear();

        object.setPosition(vel.translate(object.getPosition()));
        object.setVelocity(vel);
    }

    @Override
    public void addInput(final Input input) {
        this.inputList.add(input);
    }

}
