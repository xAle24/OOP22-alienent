package it.unibo.alienenterprises.model.api.components;

import java.util.function.Supplier;

import it.unibo.alienenterprises.model.api.GameObject;

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
    Supplier<GameObject> getProjectileSupplier();

    /**
     * Change the projectile Supplier
     * 
     * @param pSupplier new supplier
     */
    void setProjectileSupplier(Supplier<GameObject> pSupplier);

    /**
     * @return the delay between two shots
     */
    double getDelay();

    /**
     * Set a new value for the delay between two shots
     */
    void setDelay(double delay);
}
