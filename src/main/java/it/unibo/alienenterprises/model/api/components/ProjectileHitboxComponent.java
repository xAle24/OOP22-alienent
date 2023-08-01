package it.unibo.alienenterprises.model.api.components;

public interface ProjectileHitboxComponent extends HitboxComponent{
    
    Type getShooter();

    void setShooter(Type type);
}
