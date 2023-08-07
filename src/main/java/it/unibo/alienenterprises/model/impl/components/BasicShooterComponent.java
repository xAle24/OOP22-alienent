package it.unibo.alienenterprises.model.impl.components;

import java.util.Optional;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

public class BasicShooterComponent extends ComponentAbs implements ShooterComponent {

    private Supplier<GameObject> shoot;
    private double delay;
    private double counter = 0;

    private int damage;
    private int speed;

    private boolean trigger = false;

    public BasicShooterComponent(final GameObject object, final boolean enabled, final Supplier<GameObject> shot,
            final double delay) {
        super(object, enabled);
        this.shoot = shot;
        this.delay = delay;
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
            var obj = getGameObject();
            var p = shoot.get();
            p.setStatValue(Statistic.DAMAGE, damage);
            p.setStatValue(Statistic.SPEED, speed);
            p.setVelocity(Vector2D.fromAngleAndModule(obj.getVelocity().getAngle(), deltatime));
            p.setPosition(obj.getPosition());
        }
    }

    @Override
    public void start() {
        this.damage = getGameObject().getStatValue(Statistic.DAMAGE);
        this.speed = getGameObject().getStatValue(Statistic.PROJECTILESPEED);
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
        return Optional.of(new BasicShooterComponent(obj, true, shoot, obj.getStatValue(Statistic.COOLDOWN)));
    }

}
