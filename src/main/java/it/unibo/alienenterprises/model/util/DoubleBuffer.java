package it.unibo.alienenterprises.model.util;

import java.util.Set;

/**
 * DoubleBuffer interface.
 * 
 * @param <T> the Object that will be contained in the two sets.
 * 
 * @author Giulia Bonifazi
 */
public interface DoubleBuffer<T> {

    /**
     * Sets the curr as the buffer. The buffer and the curr contain the same
     * elements after this method has been called.
     */
    void changeBuffer();

    /**
     * Gets curr Set.
     * 
     * @return the current active set.
     */
    Set<T> getCurr();

    /**
     * Gets buffer Set.
     * 
     * @return return the current buffer set.
     */
    Set<T> getBuff();
}
