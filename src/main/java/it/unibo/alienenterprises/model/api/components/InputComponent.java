package it.unibo.alienenterprises.model.api.components;

import java.util.Optional;

import it.unibo.alienenterprises.model.api.InputSupplier;

/**
 * InputComponent.
 */
public interface InputComponent extends Component {

    /**
     * @return the input supplier of the component if any
     */
    public Optional<InputSupplier> getInputSupplier();

    /**
     * Set the given input supplier if neded
     * 
     * @param inputSupplier
     */
    public void setInputSupplier(InputSupplier inputSupplier);

}
