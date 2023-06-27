package it.unibo.alienenterprises.model;

import java.util.List;
import java.util.Map;

import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.ProjectileFactory;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;

public class ProjectileFactoryImpl implements ProjectileFactory {

    private class ProjectileBaseImpl extends GameObjectAbs implements Projectile {

        private boolean isAlive = true;

        public ProjectileBaseImpl(Point2D pos, Vector2D vector, HitboxComponent hitbox, InputComponent input,
                Map<Statistic, Integer> stat) {
            super(pos, vector, hitbox, input, stat);
        }

        @Override
        public Projectile spawn(int damage, int maxSpeed, Point2D position, double angle) {
            var vel = Vector2D.fromAngleAndModule(angle, getVelocity().getModule());
            var input = this.getComponent(InputComponent.class);
            var hitbox = this.getComponent(HitboxComponent.class);
            var stat = this.getStats();
            return new ProjectileBaseImpl(position, vel, hitbox.orElseThrow(), input.orElseThrow(), stat);
        }

        @Override
        public boolean isAlive() {
            return isAlive;
        }

    }

    @Override
    public Projectile getBasicProjectile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBasicProjectile'");
    }

}
