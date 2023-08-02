package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;

public class SimpleProjectileHitboxComponentImpl extends CircleHitboxComponentAbs implements ProjectileHitboxComponent{

    Type shooter;

    public SimpleProjectileHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(HitboxComponent hitbox) {
        if (hitbox.getType() != Type.PROJECTILE && hitbox.getType() != shooter) {
            hitbox.getGameObject().hit(super.getGameObject().getStatValue(Statistic.DAMAGE));
        }
    }

    @Override
    public Type getShooter() {
        return shooter;
    }

    @Override
    public void setShooter(Type type) {
        shooter = type;
    }

    @Override
    public Optional<Component> duplicate(GameObject obj) {
        return Optional.empty();
    }
    
}
