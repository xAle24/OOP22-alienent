package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.InputSupplier;

/**
 * PlayerInputComponent.
 */
public interface PlayerInputComponent extends InputComponent {

    /**
     * @return the input supplier of the component if any
     */
    InputSupplier getInputSupplier();

    /**
     * Set the given input supplier if neded.
     * 
     * @param inputSupplier
     */
    void setInputSupplier(InputSupplier inputSupplier);

    /**
     * @return the Optional of the ShooterComponent binded whith the InputComponent,
     *         or an empty Optional if not set
     */
    Optional<ShooterComponent> getShooterComponent();

}
