package it.unibo.alienenterprises.model.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the double buffer using a {@link HashSet}.
 * 
 * @param <T> the Object that will be contained in the sets.
 * 
 * @author Giulia Bonifazi
 */
public class SetDoubleBuffer<T> implements DoubleBuffer<T> {
    private Set<T> buff;
    private Set<T> curr;

    /**
     * 
     * Creates new instance of this class.
     */
    public SetDoubleBuffer() {
        this.buff = new HashSet<>();
        this.curr = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeBuffer() {
        this.curr = this.buff;
        this.buff = new HashSet<>(this.curr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<T> getCurr() {
        return this.curr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<T> getBuff() {
        return this.buff;
    }
}
