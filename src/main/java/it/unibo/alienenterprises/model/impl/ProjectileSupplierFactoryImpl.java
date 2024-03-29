package it.unibo.alienenterprises.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;
import it.unibo.alienenterprises.model.api.ProjectileSupplierFactory;
import it.unibo.alienenterprises.model.api.Statistic;
import it.unibo.alienenterprises.model.world.World;
import it.unibo.alienenterprises.model.api.components.HitboxComponent;
import it.unibo.alienenterprises.model.api.components.InputComponent;
import it.unibo.alienenterprises.model.geometry.Point2D;
import it.unibo.alienenterprises.model.geometry.Vector2D;
import it.unibo.alienenterprises.model.impl.components.StraightInputComponent;
import it.unibo.alienenterprises.model.impl.components.hitbox.SimpleProjectileHitboxComponentImpl;

/**
 * ProjectileSupplierFactoryImpl.
 * Implementation of ProjectileSupplierFactory.
 */
public class ProjectileSupplierFactoryImpl implements ProjectileSupplierFactory {

    private static final String SIMPLE_PROJECTILE = "simpleProjectile";
    private final World world;

    /**
     * Set up a ProjectileSupplierFactory.
     * 
     * @param world the world in which the projectile must be spawn
     */
    public ProjectileSupplierFactoryImpl(final World world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Supplier<GameObject> getBasicProjectileSupplier() {
        return new Supplier<GameObject>() {

            @Override
            public GameObject get() {
                final Map<Statistic, Integer> stat = new HashMap<>();
                stat.put(Statistic.HP, 1);
                final GameObject basicProjectile = new GameObjectImpl(Point2D.ORIGIN, Vector2D.NULL_VECTOR, stat,
                        SIMPLE_PROJECTILE);
                final InputComponent input = new StraightInputComponent(basicProjectile, true);
                final HitboxComponent hitbox = new SimpleProjectileHitboxComponentImpl(basicProjectile, true,
                        HitboxComponent.Type.PROJECTILE, 5);
                basicProjectile.addComponent(input);
                basicProjectile.addComponent(hitbox);
                world.addGameObject(basicProjectile);
                return basicProjectile;
            }

        };
    }

}
