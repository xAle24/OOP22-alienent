package it.unibo.alienenterprises.model.api.components;

import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.Projectile;

/**
 * ShooterComponent
 */
public interface ShooterComponent extends Component {

    /**
     * Tell the component to shoot in the next frame
     */
    void shoot();

    /**
     * Change the projectile Supplier
     * 
     * @param pSupplier new supplier
     */
    void setProjectileSupplier(Supplier<Projectile> pSupplier);
}
