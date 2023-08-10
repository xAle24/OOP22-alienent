package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;
import it.unibo.alienenterprises.model.api.components.HitboxComponent.Type;
import it.unibo.alienenterprises.model.geometry.Vector2D;

/**
 * BasicShooterComponent.
 * A simple implementation of ShooterComponent.
 */
public class BasicShooterComponent extends ComponentAbs implements ShooterComponent {

    private Supplier<GameObject> shoot;
    private int delay;
    private double counter;

    private int damage;
    private int speed;
    private Type shooterType;

    private boolean trigger;

    /**
     * @param object  the referenced object
     * @param enabled if the component must be enabled
     * @param shot    the supplier of the projectiles
     */
    public BasicShooterComponent(final GameObject object, final boolean enabled, final Supplier<GameObject> shot) {
        super(object, enabled);
        this.shoot = shot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltatime) {
        if (!isEnabled()) {
            return;
        }
        if (this.counter < this.delay) {
            this.counter = this.counter + deltatime;
        }
        if (this.trigger && this.counter >= delay) {
            final var p = shoot.get();
            p.setStatValue(Statistic.DAMAGE, damage);
            p.setStatValue(Statistic.SPEED, speed);
            final var hb = p.getComponent(ProjectileHitboxComponent.class);
            if (hb.isPresent()) {
                hb.get().setShooter(this.shooterType);
            }
            p.setVelocity(Vector2D.fromAngleAndModule(getGameObject().getVelocity().getAngle(), speed));
            p.setPosition(getGameObject().getPosition());
            p.getAllComponent().forEach((c) -> c.start());
            this.counter = 0;
        }
        this.trigger = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.damage = getGameObject().getStatValue(Statistic.DAMAGE);
        this.speed = getGameObject().getStatValue(Statistic.PROJECTILESPEED);
        this.delay = getGameObject().getStatValue(Statistic.COOLDOWN);
        this.shooterType = getGameObject().getComponent(HitboxComponent.class)
                .orElseThrow(() -> new IllegalStateException("The hitbox component isn't present")).getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        this.trigger = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<GameObject> getProjectileSupplier() {
        return shoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProjectileSupplier(final Supplier<GameObject> pSupplier) {
        this.shoot = pSupplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new BasicShooterComponent(obj, true, shoot));
    }

}
