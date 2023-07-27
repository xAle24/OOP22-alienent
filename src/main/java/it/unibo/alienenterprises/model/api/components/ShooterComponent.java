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
     * @return the projectileSupplier
     */
    Supplier<Projectile> getProjectileSupplier();

    /**
     * Change the projectile Supplier
     * 
     * @param pSupplier new supplier
     */
    void setProjectileSupplier(Supplier<Projectile> pSupplier);

    /**
     * @return the delay between two shots
     */
    double getDelay();

    /**
     * Set a new value for the delay between two shots
     */
    void setDelay(double delay);
}
