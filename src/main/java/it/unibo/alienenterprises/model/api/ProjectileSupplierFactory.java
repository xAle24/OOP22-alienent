package it.unibo.alienenterprises.model.api;

import java.util.function.Supplier;

/**
 * ProjectileSupplierFactory.
 * Creates suppliers for various tipes of prjectiles
 */
public interface ProjectileSupplierFactory extends ObjectFactory {

    /**
     * @return a supplier of basicProjectile
     */
    Supplier<GameObject> getBasicProjectileSupplier();

}
