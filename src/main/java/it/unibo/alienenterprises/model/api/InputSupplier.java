package it.unibo.alienenterprises.model.api;

import java.util.List;

/**
 * InputSupplier
 */
public interface InputSupplier {

    public enum Input {
        /**
         * Called when the player must accelerate.
         */
        ACCELERATE,
        /**
         * Called when the player stops accelerating.
         */
        STOP_ACCELERATE,
        /**
         * Called when player turns left.
         */
        TURN_LEFT,
        /**
         * Called when player turns right.
         */
        TURN_RIGHT,
        /**
         * Called when player must shoot.
         */
        SHOOT,
        /**
         * Called if the input doesn't have a function.
         */
        NOTHING;
    }

    /**
     * @return the list of all the inputs given
     */
    List<Input> getInputSet();

    /**
     * Add the given input to the input list
     */
    void addInput(Input input);

    /**
     * Remove all the previous inputs from the input list
     */
    void clearInputSet();

}
