package it.unibo.alienenterprises.model.impl.components.hitbox;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.ProjectileHitboxComponent;

public class BomberHitboxComponentImpl extends CircleHitboxComponentAbs{

    private static final int AUTOKILLDAMAGE = 99;

    public BomberHitboxComponentImpl(GameObject object, boolean enabled, Type objectType, int radius) {
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
    
}
