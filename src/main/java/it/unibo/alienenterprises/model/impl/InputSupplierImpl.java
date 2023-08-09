package it.unibo.alienenterprises.model.impl;

import java.util.ArrayList;
import java.util.List;
import it.unibo.alienenterprises.model.api.InputSupplier;

/**
 * InputSupplier standard.
 */
public class InputSupplierImpl implements InputSupplier {

    private final List<Input> inputList = new ArrayList<>();

    /**
     * 
     */
    public InputSupplierImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Input> getInputSet() {
        return List.copyOf(inputList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInput(final Input input) {
        this.inputList.add(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearInputSet() {
        this.inputList.clear();
    }

}
