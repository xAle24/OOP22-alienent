package it.unibo.alienenterprises.model.impl.components;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.InputSupplier;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.PlayerInputComponent;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.api.InputSupplier.Input;

/**
 * PlayerInputComponent
 * Handle the movements of the player.
 */
public class PlayerInputComponentImpl extends ComponentAbs implements PlayerInputComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerInputComponentImpl.class);

    private static final double ANG_VEL = 360;
    private static final double ACC_RATE = 3;

    private InputSupplier input;
    private Optional<ShooterComponent> shooter;

    private int maxSpeed;
    private double acc;

    /**
     * @param object the referenced object
     * @param input
     */
    public PlayerInputComponentImpl(final GameObject object, final InputSupplier input) {
        super(object, true);
        this.input = input;
        this.shooter = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltatime) {
        var vel = getGameObject().getVelocity();
        for (final Input in : input.getInputSet()) {
            final var module = vel.getModule();
            switch (in) {
                case ACCELERATE:
                    if (module < maxSpeed) {
                        final double accTime = acc * deltatime;
                        if (module + accTime > maxSpeed) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), maxSpeed);
                        } else {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), module + accTime);
                        }
                    }
                    break;
                case STOP_ACCELERATE:
                    if (module > 0) {
                        final double accTime = acc * deltatime;
                        if (module - accTime < 0) {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), 0);
                        } else {
                            vel = Vector2D.fromAngleAndModule(vel.getAngle(), module - accTime);
                        }
                    }
                    break;
                case TURN_LEFT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() - (ANG_VEL * deltatime), module);
                    break;
                case TURN_RIGHT:
                    vel = Vector2D.fromAngleAndModule(vel.getAngle() + (ANG_VEL * deltatime), module);
                    break;
                case SHOOT:
                    if (shooter.isPresent()) {
                        shooter.get().shoot();
                    }
                    break;
                default:
                    break;
            }
        }

        input.clearInputSet();

        getGameObject().setPosition(vel.mul(deltatime).translate(getGameObject().getPosition()));
        getGameObject().setVelocity(vel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.maxSpeed = getGameObject().getStatValue(Statistic.SPEED);
        this.acc = this.maxSpeed * ACC_RATE;
        this.shooter = getGameObject().getComponent(ShooterComponent.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputSupplier getInputSupplier() {
        return this.input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInputSupplier(final InputSupplier inputSupplier) {
        this.input = inputSupplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ShooterComponent> getShooterComponent() {
        return this.shooter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        try {
            final var retInput = input.getClass().getConstructor().newInstance();
            return Optional.of(new PlayerInputComponentImpl(obj, retInput));
        } catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LOGGER.error("Couldn't duplicate :" + getClass().getName(), e);
        }
        return Optional.empty();
    }
}
