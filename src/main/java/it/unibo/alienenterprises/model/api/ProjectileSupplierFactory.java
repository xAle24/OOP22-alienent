package it.unibo.alienenterprises.model.api;

import java.util.function.Supplier;

public interface ProjectileSupplierFactory {
    
    Supplier<Projectile> getBasicProjectileSupplier();

}
