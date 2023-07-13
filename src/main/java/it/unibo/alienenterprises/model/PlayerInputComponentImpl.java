package it.unibo.alienenterprises.model;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.InputSupplier;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.api.InputSupplier.Input;
/**
 * PlayerInputComponent
 * Handle the movements of the player.
 */
public class PlayerInputComponentImpl extends ComponentAbs implements InputComponent {


    private static final double ANG_VEL = 1;

    private final InputSupplier input;

    private int maxSpeed;
    private double acc;

    public PlayerInputComponentImpl(final GameObject object, final InputSupplier input) {
        super(object, true);
        this.input = input;
    }

    @Override
    public void update(final double deltatime) {
        var vel = getGameObject().getVelocity();
        for (Input in : input.getInputList()) {
            final var module = vel.getModule();
            switch (in) {
                case ACCELERATE:
                    if (module < maxSpeed) {
                        final double accTime = acc*deltatime;
                        if (module + accTime > maxSpeed) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), maxSpeed);
                        }
                        vel = Vector2D.fromAngleAndModule(vel.getAngle(), module + accTime);
                    }
                    break;
                case STOP_ACCELERATE:
                    if (module > 0) {
                        final double accTime = acc*deltatime;
                        if (module - accTime < 0) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), 0);
                        }
                        vel = Vector2D.fromAngleAndModule(vel.getAngle(), module - accTime);
                    }
                    break;
                case TURN_LEFT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() + (ANG_VEL*deltatime), module);
                    break;
                case TURN_RIGHT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() - (ANG_VEL*deltatime), module);
                    break;
                default:
                    break;
            }
        }

        input.clearInputList();

        var tran = new Vector2D(vel.getxComp()*deltatime, vel.getxComp()*deltatime);

        getGameObject().setPosition(tran.translate(getGameObject().getPosition()));
        getGameObject().setVelocity(vel);
    }

    @Override
    public void start(){
        this.maxSpeed = getGameObject().getStatValue(Statistic.SPEED);
        this.acc = this.maxSpeed/30;
    }

}
