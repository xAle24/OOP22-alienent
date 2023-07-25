package it.unibo.alienenterprises.model.api;

import java.util.HashSet;
import java.util.Set;

/**
 * InputSupplier standard
 */
public class InputSupplierImpl implements InputSupplier{

    private final Set<Input> inputSet = new HashSet<>();

    /**
     * 
     */
    public InputSupplierImpl() {
    }

    @Override
    public Set<Input> getInputSet() {
        return Set.copyOf(inputSet);
    }

    @Override
    public void addInput(final Input input) {
        this.inputSet.add(input);
    }

    @Override
    public void clearInputSet() {
        this.inputSet.clear();
    }
    
}
