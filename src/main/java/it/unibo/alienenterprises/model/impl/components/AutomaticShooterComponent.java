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
 * AutomaticShooterComponent.
 * A shooter component that shoot automatically.
 */
public class AutomaticShooterComponent extends ComponentAbs implements ShooterComponent {

    private int delay;
    private int projectileSpeed;
    private Type shooterType;
    private int damage;

    private double count;

    private Supplier<GameObject> shot;

    /**
     * @param object  the referenced object
     * @param enabled if the component must be enabled
     * @param shot    the supplier of the projectiles
     */
    public AutomaticShooterComponent(final GameObject object, final boolean enabled, final Supplier<GameObject> shot) {
        super(object, enabled);
        this.shot = shot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.delay = getGameObject().getStatValue(Statistic.COOLDOWN);
        this.projectileSpeed = getGameObject().getStatValue(Statistic.PROJECTILESPEED);
        this.shooterType = getGameObject().getComponent(HitboxComponent.class)
                .orElseThrow(() -> new IllegalStateException("The hitbox component isn't present")).getType();
        this.damage = getGameObject().getStatValue(Statistic.DAMAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double deltatime) {
        this.count = this.count + deltatime;
        if (this.count >= this.delay) {
            final var p = this.shot.get();
            p.setStatValue(Statistic.SPEED, projectileSpeed);
            final var hb = p.getComponent(ProjectileHitboxComponent.class);
            if (hb.isPresent()) {
                hb.get().setShooter(this.shooterType);
            }
            p.setStatValue(Statistic.DAMAGE, this.damage);
            p.setVelocity(Vector2D.fromAngleAndModule(getGameObject().getVelocity().getAngle(), projectileSpeed));
            p.setPosition(getGameObject().getPosition());
            p.getAllComponent().forEach((c) -> c.start());
            this.count = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot() {
        // this component shoots automatically
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<GameObject> getProjectileSupplier() {
        return this.shot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProjectileSupplier(final Supplier<GameObject> pSupplier) {
        this.shot = pSupplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new AutomaticShooterComponent(obj, isEnabled(), shot));
    }

}
