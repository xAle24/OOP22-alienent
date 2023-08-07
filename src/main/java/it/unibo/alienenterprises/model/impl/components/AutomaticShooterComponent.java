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

public class AutomaticShooterComponent extends ComponentAbs implements ShooterComponent {

    private int delay;
    private int projectileSpeed;
    private Type shooterType;
    private int damage;

    private double count = 0;

    private Supplier<GameObject> shot;

    private boolean mustShoot = false;

    public AutomaticShooterComponent(final GameObject object, final boolean enabled, final Supplier<GameObject> shot) {
        super(object, enabled);
        this.shot = shot;
    }

    @Override
    public void start() {
        this.delay = getGameObject().getStatValue(Statistic.COOLDOWN);
        this.projectileSpeed = getGameObject().getStatValue(Statistic.PROJECTILESPEED);
        this.shooterType = getGameObject().getComponent(HitboxComponent.class).get().getType();//TODO
        this.damage = getGameObject().getStatValue(Statistic.DAMAGE);
    }

    @Override
    public void update(final double deltatime) {
        this.count = this.count + deltatime;
        if(this.count>=this.delay || this.mustShoot){
            var p = this.shot.get();
            p.setStatValue(Statistic.SPEED, projectileSpeed);
            var hb = p.getComponent(ProjectileHitboxComponent.class);
            if(hb.isPresent()){
                hb.get().setShooter(this.shooterType);
            }
            p.setStatValue(Statistic.DAMAGE, damage);
            p.setVelocity(Vector2D.fromAngleAndModule(getGameObject().getVelocity().getAngle(), projectileSpeed));
            p.setPosition(getGameObject().getPosition());
            this.count = 0;
        }
    }

    @Override
    public void shoot() {
        this.mustShoot = true;
    }

    @Override
    public Supplier<GameObject> getProjectileSupplier() {
        return this.shot;
    }

    @Override
    public void setProjectileSupplier(Supplier<GameObject> pSupplier) {
        this.shot = pSupplier;
    }


    @Override
    public Optional<Component> duplicate(final GameObject obj) {
        return Optional.of(new AutomaticShooterComponent(obj, isEnabled(), shot));
    }

}
