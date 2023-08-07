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
 * BasicShooterComponent
 * A simple implementation of ShooterComponent
 */
public class BasicShooterComponent extends ComponentAbs implements ShooterComponent {

    private Supplier<GameObject> shoot;
    private int delay;
    private double counter = 0;

    private int damage;
    private int speed;
    private Type shooterType;

    private boolean trigger = false;

    /**
     * @param object  the referenced object
     * @param enabled if the component must be enabled
     * @param shot    the supplier of the projectiles
     */
    public BasicShooterComponent(final GameObject object, final boolean enabled, final Supplier<GameObject> shot) {
        super(object, enabled);
        this.shoot = shot;
    }

    @Override
    public void update(final double deltatime) {
        if (!isEnabled()) {
            return;
        }
        if (this.counter < this.delay) {
            this.counter = this.counter + deltatime;
        }
        if (this.trigger && this.counter >= delay) {
            var p = shoot.get();
            p.setStatValue(Statistic.DAMAGE, damage);
            p.setStatValue(Statistic.SPEED, speed);
            var hb = p.getComponent(ProjectileHitboxComponent.class);
            if (hb.isPresent()) {
                hb.get().setShooter(this.shooterType);
            }
            p.setVelocity(Vector2D.fromAngleAndModule(getGameObject().getVelocity().getAngle(), speed));
            p.setPosition(getGameObject().getPosition());
        }
    }

    @Override
    public void start() {
        this.damage = getGameObject().getStatValue(Statistic.DAMAGE);
        this.speed = getGameObject().getStatValue(Statistic.PROJECTILESPEED);
        this.delay = getGameObject().getStatValue(Statistic.COOLDOWN);
        this.shooterType = getGameObject().getComponent(HitboxComponent.class).get().getType();// TODO
    }

    @Override
    public void shoot() {
        this.trigger = true;
    }

    @Override
    public Supplier<GameObject> getProjectileSupplier() {
        return shoot;
    }

    @Override
    public void setProjectileSupplier(final Supplier<GameObject> pSupplier) {
        this.shoot = pSupplier;
    }

    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new BasicShooterComponent(obj, true, shoot));
    }

}
