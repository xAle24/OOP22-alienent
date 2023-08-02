package it.unibo.alienenterprises.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.alienenterprises.model.api.InputSupplier;

/**
 * InputSupplier standard
 */
public class InputSupplierImpl implements InputSupplier {

    private final List<Input> inputList = new ArrayList<>();

    /**
     * 
     */
    public InputSupplierImpl() {
    }

    @Override
    public List<Input> getInputSet() {
        return List.copyOf(inputList);
    }

    @Override
    public void addInput(final Input input) {
        this.inputList.add(input);
    }

    @Override
    public void clearInputSet() {
        this.inputList.clear();
    }

}
