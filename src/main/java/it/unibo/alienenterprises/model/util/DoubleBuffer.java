package it.unibo.alienenterprises.model.util;

import java.util.Set;

/**
 * Double buffer interface.
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
     * @return
     */
    Set<T> getCurr();

    /**
     * Gets buffer Set.
     * 
     * @return
     */
    Set<T> getBuff();
}
