package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.InputSupplier;

/**
 * PlayerInputComponent
 */
public interface PlayerInputComponent extends InputComponent {

    /**
     * @return the input supplier of the component if any
     */
    public InputSupplier getInputSupplier();

    /**
     * Set the given input supplier if neded
     * 
     * @param inputSupplier
     */
    public void setInputSupplier(InputSupplier inputSupplier);

    /**
     * @return
     */
    public Optional<ShooterComponent> getShooterComponent();

}
