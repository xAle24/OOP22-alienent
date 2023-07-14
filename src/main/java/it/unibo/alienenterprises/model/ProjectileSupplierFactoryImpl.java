package it.unibo.alienenterprises.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.Projectile;
import it.unibo.alienenterprises.model.api.ProjectileSupplierFactory;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.Component;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;

public class ProjectileSupplierFactoryImpl implements ProjectileSupplierFactory {

    private class ProjectileBaseImpl extends GameObjectAbs implements Projectile {

        private boolean isAlive = true;

        public ProjectileBaseImpl(final Point2D pos, final Vector2D vector, final Map<Statistic, Integer> stat, final Component... components) {
            super(pos, vector, stat, components);
        }

        @Override
        public boolean isAlive() {
            return isAlive;
        }

    }

    @Override
    public Supplier<Projectile>  getBasicProjectileSupplier() {
        final Map<Statistic, Integer> stat = new HashMap<>();
        stat.put(Statistic.HP, 1);
        final Projectile basicProjectile = new ProjectileBaseImpl(Point2D.ORIGIN, Vector2D.NULL_VECTOR, stat);
        final InputComponent input = new StraightInputComponent(basicProjectile,true);
        final HitboxComponent hitbox = null;
        basicProjectile.addComponent(input);
        basicProjectile.addComponent(hitbox);
        return basicProjectile;
    }

}
