package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.ProjectileFactory;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;

public class ProjectileFactoryImpl implements ProjectileFactory {

    private class ProjectileBaseImpl extends GameObjectAbs implements Projectile {

        private boolean isAlive = true;

        public ProjectileBaseImpl(final Point2D pos, final Vector2D vector, final HitboxComponent hitbox,
                final InputComponent input,
                final Map<Statistic, Integer> stat) {
            super(pos, vector, hitbox, input, stat);
        }

        @Override
        public Projectile spawn(final Point2D position, final double angle, final int damage, final int speed) {
            var vel = Vector2D.fromAngleAndModule(angle, getVelocity().getModule());
            var input = this.getComponent(InputComponent.class);
            var hitbox = this.getComponent(HitboxComponent.class);
            var stat = this.getStats();
            stat.put(Statistic.DAMAGE, damage);
            stat.put(Statistic.SPEED, speed);
            return new ProjectileBaseImpl(position, vel, hitbox.orElseThrow(), input.orElseThrow(), stat);
        }

        @Override
        public boolean isAlive() {
            return isAlive;
        }

        

    }

    private Optional<Projectile> basicProjectile = Optional.empty();

    @Override
    public Projectile getBasicProjectilePrototype() {
        if (basicProjectile.isEmpty()) {
            final InputComponent input = new StraightInputComponent();
            final HitboxComponent hitbox = null;
            final Map<Statistic, Integer> stat = new HashMap<>();
            stat.put(Statistic.HP, 1);
            this.basicProjectile = Optional.of(new ProjectileBaseImpl(Point2D.ORIGIN, Vector2D.NULL_VECTOR, hitbox, input, stat));
        }
        return basicProjectile.get();
    }

}
