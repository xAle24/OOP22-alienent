package it.unibo.alienenterprises.model.impl.components.hitbox;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;

public class BomberHitboxComponentImpl extends CircleHitboxComponentAbs{

    private static final int AUTOKILLDAMAGE = 99;

    public BomberHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, double radius) {
        super(object, enabled, objectType, radius);
    }

    @Override
    public void isColliding(HitboxComponent hitbox) {
        if (hitbox.getType() == Type.PLAYER) {
            hitbox.getGameObject().hit(this.getGameObject().getStatValue(Statistic.DAMAGE));
            this.getGameObject().hit(AUTOKILLDAMAGE);
        }
        if (hitbox instanceof ProjectileHitboxComponent && ((ProjectileHitboxComponent)hitbox).getShooter() == Type.PLAYER) {
            hitbox.getGameObject().hit(1);
        }
    }

    @Override
    public Optional<Component> duplicate(GameObject obj) {
        return Optional.of(new BomberHitboxComponentImpl(obj, isEnabled(), Type.ENEMY, this.getHitbox().getRay()));
    }
}
