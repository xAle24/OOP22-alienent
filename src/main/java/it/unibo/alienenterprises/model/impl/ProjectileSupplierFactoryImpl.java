package it.unibo.alienenterprises.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.GameObjectAbs;
import it.unibo.alienenterprises.model.api.ProjectileSupplierFactory;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;

public class ProjectileSupplierFactoryImpl implements ProjectileSupplierFactory {

    @Override
    public Supplier<GameObject> getBasicProjectileSupplier() {
        return new Supplier<GameObject>() {

            @Override
            public GameObject get() {
                final Map<Statistic, Integer> stat = new HashMap<>();
                stat.put(Statistic.HP, 1);
                final GameObject basicProjectile = new GameObjectAbs(Point2D.ORIGIN, Vector2D.NULL_VECTOR, stat);
                final InputComponent input = new StraightInputComponent(basicProjectile, true);
                final HitboxComponent hitbox = null;
                basicProjectile.addComponent(input);
                basicProjectile.addComponent(hitbox);
                return basicProjectile;
            }

        };
    }

}
