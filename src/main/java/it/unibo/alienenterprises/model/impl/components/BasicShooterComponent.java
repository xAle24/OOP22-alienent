package it.unibo.alienenterprises.model.impl.components;

import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.ComponentAbs;
import it.unibo.alienenterprises.model.api.components.ShooterComponent;
import it.unibo.alienenterprises.model.geometry.Vector2D;

public class BasicShooterComponent extends ComponentAbs implements ShooterComponent {

    private Supplier<Projectile> shoot;
    private double delay;
    private double counter=0;

    private int damage;
    private int speed;
    
    private boolean trigger = false;

    public BasicShooterComponent(GameObject object, boolean enabled, final Supplier<Projectile> shoot, final double delay) {
        super(object, enabled);
        this.shoot=shoot;
        this.delay=delay;
    }

    @Override
    public void update(double deltatime) {
        if(!isEnabled()){
            return;
        }
        if(counter<delay){
            counter = counter + deltatime;
        }
        if(this.trigger && counter>=delay){
            var obj = getGameObject();
            var p = shoot.get();
            p.setStatValue(Statistic.DAMAGE, damage);
            p.setStatValue(Statistic.SPEED, speed);
            p.setVelocity(Vector2D.fromAngleAndModule(obj.getVelocity().getAngle(), deltatime));
            p.setPosition(obj.getPosition());
        }
    }

    @Override
    public void start(){
        this.damage=getGameObject().getStatValue(Statistic.DAMAGE);
        this.speed=getGameObject().getStatValue(Statistic.PROJECTILESPEED);
    }

    @Override
    public void shoot() {
        this.trigger=true;
    }

    @Override
    public void setProjectileSupplier(final Supplier<Projectile> pSupplier) {
        this.shoot=pSupplier;
    }



}